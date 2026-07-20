package com.erp.demo.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.erp.demo.dashboard.service.DashboardService;
import java.util.List;
import com.erp.demo.inventory.model.Inventory;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/total-sales")
    public long getTotalSales() {
        return dashboardService.getTotalSales();
    }

    @GetMapping("/total-purchases")
    public long getTotalPurchases() {
        return dashboardService.getTotalPurchases();
    }

    @GetMapping("/total-products")
    public long getTotalProducts() {
        return dashboardService.getTotalProducts();
    }

    @GetMapping("/total-customers")
    public long getTotalCustomers() {
        return dashboardService.getTotalCustomers();
    }
    
    @GetMapping("/low-stock")
    public List<Inventory> getLowStockProducts() {
        return dashboardService.getLowStockProducts();
    }
    
    @GetMapping("/revenue")
    public Double getRevenue(){
        return dashboardService.getTotalRevenue();
    }
    
    @GetMapping("/top-products")
    public List<Object[]> getTopProducts(){
        return dashboardService.getTopSellingProducts();
    }
    @GetMapping("/monthly-sales")
    public List<Object[]> getMonthlySales(){
        return dashboardService.getMonthlySales();
    }
    @GetMapping("/top-customers")
    public List<Object[]> getTopCustomers(){
        return dashboardService.getTopCustomers();
    }
 }
