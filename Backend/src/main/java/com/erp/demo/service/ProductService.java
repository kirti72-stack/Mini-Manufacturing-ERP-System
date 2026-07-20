package com.erp.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.Product;
import com.erp.demo.repository.ProductRepository;
import com.erp.demo.exception.ProductNotFoundException;
import com.erp.demo.audit.service.AuditLogService;
import com.erp.demo.inventory.model.Inventory;
import com.erp.demo.inventory.repository.InventoryRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private InventoryRepository inventoryRepository;   // ✅ ADD THIS ON TOP

    public Product saveProduct(Product product, String role, String username) {

        if (role.equals("STAFF")) {
            throw new RuntimeException("Access Denied");
        }

        Product saved = productRepository.save(product);

        Inventory inventory = new Inventory();
        inventory.setProduct(saved);
        inventory.setQuantity(0);
        inventoryRepository.save(inventory);

        auditLogService.log(username, "CREATE_PRODUCT", "PRODUCT");

        return saved;
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findByIsActiveTrue();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public void deleteProduct(Long id, String role, String username) {

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Only admin can delete");
        }

        Product product = getProductById(id);
        product.setActive(false);

        productRepository.save(product);

        auditLogService.log(username, "DELETE_PRODUCT", "PRODUCT");
    }
    
    public Product updateProduct(Long id, Product product, String role, String username) {

        if (role.equals("STAFF")) {
            throw new RuntimeException("Access Denied");
        }

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());

        auditLogService.log(username, "UPDATE_PRODUCT", "PRODUCT");

        return productRepository.save(existing);
    }
}
