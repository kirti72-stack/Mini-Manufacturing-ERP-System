package com.erp.demo.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import com.erp.demo.model.Product;
//import com.erp.demo.model.BaseEntity;
import com.erp.demo.common.BaseEntity;

@Entity
public class Inventory extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(value = 0, message = "Stock cannot be negative")
    private int quantity;

    private String warehouseLocation;

    public Inventory() {}

    

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
}