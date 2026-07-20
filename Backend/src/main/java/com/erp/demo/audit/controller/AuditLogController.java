package com.erp.demo.audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.erp.demo.audit.model.AuditLog;
import com.erp.demo.audit.repository.AuditLogRepository;

@RestController
@RequestMapping("/audit")
@CrossOrigin(origins = "*") // allow frontend
public class AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // ✅ GET ALL LOGS
    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}
