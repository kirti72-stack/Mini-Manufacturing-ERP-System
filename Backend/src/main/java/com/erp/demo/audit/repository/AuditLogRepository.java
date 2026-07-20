package com.erp.demo.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.demo.audit.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}