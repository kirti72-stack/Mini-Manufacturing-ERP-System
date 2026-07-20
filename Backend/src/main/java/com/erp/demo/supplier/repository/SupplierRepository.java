package com.erp.demo.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.demo.supplier.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
