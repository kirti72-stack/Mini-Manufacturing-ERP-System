package com.erp.demo.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.demo.purchase.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
