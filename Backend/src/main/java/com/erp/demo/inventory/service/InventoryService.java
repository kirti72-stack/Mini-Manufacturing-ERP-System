package com.erp.demo.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.inventory.model.Inventory;
import com.erp.demo.inventory.repository.InventoryRepository;
import com.erp.demo.exception.InventoryNotFoundException;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with id: " + id));
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existing = getInventoryById(id);
        existing.setProduct(inventory.getProduct());
        existing.setQuantity(inventory.getQuantity());
        existing.setWarehouseLocation(inventory.getWarehouseLocation());

        return inventoryRepository.save(existing);
    }

    public void deleteInventory(Long id) {
        Inventory inventory = getInventoryById(id);
        inventoryRepository.delete(inventory);
    }
}
