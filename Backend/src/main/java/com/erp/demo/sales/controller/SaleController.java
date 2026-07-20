package com.erp.demo.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.erp.demo.sales.model.Sale;
import com.erp.demo.sales.service.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public Sale createSale(
            @Valid @RequestBody Sale sale,
            @RequestHeader("role") String role,
            @RequestHeader("username") String username
    ) {

        // ❌ STAFF should NOT create sales
        if (role.equals("STAFF")) {
            throw new RuntimeException("Access Denied");
        }

        return saleService.createSale(sale, username);
    }

    @GetMapping
    public List<Sale> getSales() {
        return saleService.getAllSales();
    }
}