package com.payroll.compensationAndLeaves.service.backend.repository;

import com.payroll.compensationAndLeaves.service.backend.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findByEmployeeId(Long employeeId);
}
