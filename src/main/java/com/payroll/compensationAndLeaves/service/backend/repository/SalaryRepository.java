package com.payroll.compensationAndLeaves.service.backend.repository;

import com.payroll.compensationAndLeaves.service.backend.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<SalaryEntity, Long> {
    List<SalaryEntity> findByEmployeeId(Long employeeId);
}
