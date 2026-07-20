package com.erp.demo.customer.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.demo.customer.model.Customer;
import com.erp.demo.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // CREATE
    @PostMapping
    public Customer addCustomer(
            @Valid @RequestBody Customer customer,
            @RequestHeader("role") String role
    ) {

        if (role.equals("STAFF")) {
            throw new RuntimeException("Access Denied");
        }

        return customerService.saveCustomer(customer);
    }

    // GET ALL
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer,
            @RequestHeader("role") String role
    ) {

        if (role.equals("STAFF")) {
            throw new RuntimeException("Access Denied");
        }

        return customerService.updateCustomer(id, customer);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id,
            @RequestHeader("role") String role
    ) {

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Only admin can delete");
        }

        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}