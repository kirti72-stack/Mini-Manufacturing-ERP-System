package com.erp.demo.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.erp.demo.sales.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT SUM(s.price * s.quantity) FROM Sale s")
	Double getTotalRevenue();
	
	@Query("SELECT s.product.name, SUM(s.quantity) FROM Sale s GROUP BY s.product.name ORDER BY SUM(s.quantity) DESC")
	List<Object[]> getTopSellingProducts();
	
	@Query("SELECT FUNCTION('DATE_FORMAT', s.createdAt, '%Y-%m'), SUM(s.price * s.quantity) FROM Sale s GROUP BY FUNCTION('DATE_FORMAT', s.createdAt, '%Y-%m')")
	List<Object[]> getMonthlySales();
	
	@Query("SELECT s.customer.name, SUM(s.price * s.quantity) FROM Sale s GROUP BY s.customer.name ORDER BY SUM(s.price * s.quantity) DESC")
	List<Object[]> getTopCustomers();

}
