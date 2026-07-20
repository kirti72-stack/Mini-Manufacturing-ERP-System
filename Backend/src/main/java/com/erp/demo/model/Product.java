package com.erp.demo.model;

import jakarta.persistence.*;
import com.erp.demo.common.BaseEntity;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    
	@NotBlank(message = "Product name cannot be empty")
	@Pattern(
	    regexp = "^[A-Za-z0-9 ]+$",
	    message = "Product name must not contain special characters"
	)
	@Column(nullable = false)
	private String name;

    @Pattern(
    	    regexp = "^[A-Za-z0-9 ]+$",
    	    message = "Description must not contain special characters"
    	)
    	private String description;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;
    
    @Column(name = "is_active")
    private boolean isActive = true;

    public Product() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
