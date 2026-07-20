package com.erp.demo.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.purchase.model.Purchase;
import com.erp.demo.purchase.repository.PurchaseRepository;
import com.erp.demo.inventory.model.Inventory;
import com.erp.demo.inventory.repository.InventoryRepository;
import com.erp.demo.stockmovement.model.StockMovement;
import com.erp.demo.stockmovement.service.StockMovementService;
import com.erp.demo.audit.service.AuditLogService;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    private StockMovementService stockMovementService;
    
    @Autowired
    private AuditLogService auditLogService;

    public Purchase savePurchase(Purchase purchase) {

        Long productId = purchase.getProduct().getId();

        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElse(null);

        if (inventory == null) {

            inventory = new Inventory();
            inventory.setProduct(purchase.getProduct());
            inventory.setQuantity(purchase.getQuantity());
            inventory.setWarehouseLocation("Main Warehouse");

        } else {

            inventory.setQuantity(
                inventory.getQuantity() + purchase.getQuantity()
            );
        }

        inventoryRepository.save(inventory);
        
        // SAVE PURCHASE
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // RECORD STOCK MOVEMENT
        StockMovement movement = new StockMovement();
        movement.setProduct(purchase.getProduct());
        movement.setQuantity(purchase.getQuantity());
        movement.setType("PURCHASE");

        stockMovementService.saveMovement(movement);
        
        //Audit log
        auditLogService.log("ADMIN", "CREATE_PURCHASE", "PURCHASE");

        return savedPurchase;
       
    }
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .filter(p -> p.getIsActive() == null || Boolean.TRUE.equals(p.getIsActive()))
                .toList();
    }
    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public void cancelPurchase(Long id) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        // already cancelled check
        if (Boolean.FALSE.equals(purchase.getIsActive())) {
            throw new RuntimeException("Purchase already cancelled");
        }

        // 🔥 mark inactive
        purchase.setIsActive(false);
        purchaseRepository.save(purchase);

        // 🔥 REVERSE INVENTORY
        Long productId = purchase.getProduct().getId();

        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        inventory.setQuantity(
                inventory.getQuantity() - purchase.getQuantity()
        );

        inventoryRepository.save(inventory);

        // 🔥 STOCK MOVEMENT (CANCEL)
        StockMovement movement = new StockMovement();
        movement.setProduct(purchase.getProduct());
        movement.setQuantity(purchase.getQuantity());
        movement.setType("PURCHASE_CANCEL");

        stockMovementService.saveMovement(movement);
    }
}