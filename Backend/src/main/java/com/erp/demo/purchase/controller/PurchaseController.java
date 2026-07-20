package com.erp.demo.purchase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.erp.demo.purchase.model.Purchase;
import com.erp.demo.purchase.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public Purchase addPurchase(
            @Valid @RequestBody Purchase purchase,
            @RequestHeader("role") String role
    ) {
        if (role.equals("STAFF")) {
            throw new RuntimeException("Access Denied");
        }

        return purchaseService.savePurchase(purchase);
    }

    @GetMapping
    public List<Purchase> getPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }


@PutMapping("/cancel/{id}")
public void cancelPurchase(
        @PathVariable Long id,
        @RequestHeader("role") String role
) {
    if (!role.equals("ADMIN")) {
        throw new RuntimeException("Only admin can cancel purchase");
    }

    purchaseService.cancelPurchase(id);
}
}
