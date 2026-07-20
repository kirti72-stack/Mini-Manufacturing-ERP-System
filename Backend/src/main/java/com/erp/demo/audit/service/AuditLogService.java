package com.erp.demo.audit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.audit.model.AuditLog;
import com.erp.demo.audit.repository.AuditLogRepository;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void log(String username, String action, String module){

        AuditLog log = new AuditLog();

        log.setUsername(username);
        log.setAction(action);
        log.setModule(module);
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}
