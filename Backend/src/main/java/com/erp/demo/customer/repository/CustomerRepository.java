package com.erp.demo.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.demo.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
