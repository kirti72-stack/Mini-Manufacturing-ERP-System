package com.erp.demo.stockmovement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.erp.demo.stockmovement.model.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long>{

    List<StockMovement> findByProductId(Long productId);

}
