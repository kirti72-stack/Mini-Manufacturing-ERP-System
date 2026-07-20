package com.erp.demo.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.demo.inventory.model.Inventory;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import com.erp.demo.model.Product;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

Optional<Inventory> findByProductId(Long productId);

@Query("SELECT i FROM Inventory i WHERE i.quantity < 10")
List<Inventory> findLowStockProducts();
}