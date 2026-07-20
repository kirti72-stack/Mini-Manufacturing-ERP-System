package com.erp.demo.sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.sales.model.Sale;
import com.erp.demo.sales.repository.SaleRepository;
import com.erp.demo.stockmovement.service.StockMovementService;
import com.erp.demo.inventory.model.Inventory;
import com.erp.demo.inventory.repository.InventoryRepository;
import com.erp.demo.stockmovement.model.StockMovement;
import com.erp.demo.audit.service.AuditLogService;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StockMovementService stockMovementService;
    
    @Autowired
    private AuditLogService auditLogService;

    public Sale createSale(Sale sale, String username) {

        // ✅ CHECK PRODUCT EXISTS IN REQUEST
        if (sale.getProduct() == null || sale.getProduct().getId() == null) {
            throw new RuntimeException("Product ID is required");
        }

        Long productId = sale.getProduct().getId();

        // ✅ FETCH INVENTORY
        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for this product"));

        // ✅ CHECK STOCK AVAILABILITY
        if (inventory.getQuantity() < sale.getQuantity()) {
            throw new RuntimeException("Not enough stock available");
        }

        // ✅ REDUCE STOCK
        inventory.setQuantity(inventory.getQuantity() - sale.getQuantity());
        inventoryRepository.save(inventory);

        // ✅ SAVE SALE
        Sale savedSale = saleRepository.save(sale);

        // ✅ RECORD STOCK MOVEMENT
        StockMovement movement = new StockMovement();
        movement.setProduct(sale.getProduct());
        movement.setQuantity(sale.getQuantity());
        movement.setType("SALE");

        stockMovementService.saveMovement(movement);
        
        //audit log
        auditLogService.log(username, "CREATE_SALE", "SALES");

        return savedSale;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}