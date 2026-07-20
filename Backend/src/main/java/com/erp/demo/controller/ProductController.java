package com.erp.demo.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.erp.demo.model.Product;
import com.erp.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(
        @Valid @RequestBody Product product,
        @RequestHeader("role") String role,
        @RequestHeader("username") String username
    ) {
        return productService.saveProduct(product, role, username);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    
 // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
        @PathVariable Long id,
        @RequestBody Product product,
        @RequestHeader("role") String role,
        @RequestHeader("username") String username
    ) {
        return productService.updateProduct(id, product, role, username);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public String deleteProduct(
        @PathVariable Long id,
        @RequestHeader("role") String role,
        @RequestHeader("username") String username
    ) {
        productService.deleteProduct(id, role, username);
        return "Product deleted successfully";
    }
}

