package com.erp.demo.supplier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.supplier.model.Supplier;
import com.erp.demo.supplier.repository.SupplierRepository;


@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    
    public Supplier saveSupplier(Supplier supplier, String role, String username) {

        if (role.equals("STAFF")) {
            throw new RuntimeException("Access Denied");
        }

        Supplier saved = supplierRepository.save(supplier);

        return saved;
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();

        return suppliers.stream()
        		.filter(s -> Boolean.TRUE.equals(s.getIsActive()))
                .toList();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public void deleteSupplier(Long id, String role, String username) {

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Only admin can delete");
        }

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setIsActive(false);
        supplierRepository.save(supplier);
    }
    
    public Supplier updateSupplier(Long id, Supplier supplier, String role, String username) {

        if (role.equals("STAFF")) {
            throw new RuntimeException("Access Denied");
        }

        Supplier existing = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));

        existing.setName(supplier.getName());
        existing.setEmail(supplier.getEmail());
        existing.setPhone(supplier.getPhone());
        existing.setCompany(supplier.getCompany());
        existing.setAddress(supplier.getAddress());

        return supplierRepository.save(existing);
    }
  }
