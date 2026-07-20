package com.erp.demo.supplier.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.erp.demo.common.BaseEntity;

@Entity
public class Supplier extends BaseEntity {

    @NotBlank(message = "Supplier name cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(length = 10)
    private String phone;

    @NotBlank(message = "Company cannot be empty")
    @Pattern(
    	    regexp = "^[A-Za-z0-9 ]+$",
    	    message = "Company must not contain special characters"
    	)
    	@Column(nullable = false)
    	private String company;

    @NotBlank(message = "Address cannot be empty")
    @Pattern(
    	    regexp = "^[A-Za-z0-9, ]+$",
    	    message = "Address must not contain special characters except comma"
    	)
    @Column(nullable = false)
    private String address;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @PrePersist
    public void prePersist() {
        if (isActive == null) {
            isActive = true;
        }
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
