package com.erp.demo.stockmovement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.erp.demo.stockmovement.model.StockMovement;
import com.erp.demo.stockmovement.repository.StockMovementRepository;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository repository;

    public StockMovement saveMovement(StockMovement movement){
        return repository.save(movement);
    }

    public List<StockMovement> getProductHistory(Long productId){
        return repository.findByProductId(productId);
    }
}
