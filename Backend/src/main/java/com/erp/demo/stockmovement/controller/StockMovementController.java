package com.erp.demo.stockmovement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.erp.demo.stockmovement.model.StockMovement;
import com.erp.demo.stockmovement.service.StockMovementService;

@RestController
@RequestMapping("/inventory")
public class StockMovementController {

    @Autowired
    private StockMovementService service;

    @GetMapping("/history/{productId}")
    public List<StockMovement> getHistory(@PathVariable Long productId){
        return service.getProductHistory(productId);
    }
}
