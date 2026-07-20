package com.erp.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByIsActiveTrue();
}
