package com.erp.demo.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.customer.repository.CustomerRepository;
import com.erp.demo.inventory.model.Inventory;
import com.erp.demo.inventory.repository.InventoryRepository;
import com.erp.demo.purchase.repository.PurchaseRepository;
import com.erp.demo.repository.ProductRepository;
import com.erp.demo.sales.repository.SaleRepository;


@Service
public class DashboardService {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	public List<Inventory> getLowStockProducts(){
	    return inventoryRepository.findLowStockProducts();
	}
	
	public List<Object[]> getTopSellingProducts(){
	    return saleRepository.getTopSellingProducts();
	}
	
	public List<Object[]> getMonthlySales(){
	    return saleRepository.getMonthlySales();
	}
	
	public List<Object[]> getTopCustomers(){
	    return saleRepository.getTopCustomers();
	}

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public long getTotalSales() {
        return saleRepository.count();
    }

    public long getTotalPurchases() {
        return purchaseRepository.count();
    }

    public long getTotalProducts() {
        return productRepository.count();
    }

    public long getTotalCustomers() {
        return customerRepository.count();
    }
    
    public Double getTotalRevenue(){
        return saleRepository.getTotalRevenue();
    }
}
