package com.erp.demo.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.erp.demo.supplier.model.Supplier;
import com.erp.demo.supplier.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public Supplier addSupplier(
        @Valid @RequestBody Supplier supplier,
        @RequestHeader("role") String role,
        @RequestHeader("username") String username
    ) {
        return supplierService.saveSupplier(supplier, role, username);
    }

    @GetMapping
    public List<Supplier> getSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(
        @PathVariable Long id,
        @RequestHeader("role") String role,
        @RequestHeader("username") String username
    ) {
        supplierService.deleteSupplier(id, role, username);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public Supplier updateSupplier(
        @PathVariable Long id,
        @RequestBody Supplier supplier,
        @RequestHeader("role") String role,
        @RequestHeader("username") String username
    ) {
        return supplierService.updateSupplier(id, supplier, role, username);
    }
}
