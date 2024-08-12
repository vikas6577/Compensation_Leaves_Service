package com.payroll.compensationAndLeaves.service.backend.service;


import com.payroll.compensationAndLeaves.service.backend.entity.Salary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalaryService{
    Salary insertSalary(Salary salary);

    Salary updateSalary(Long id, Salary updatedSalary);

    List<Salary> getAllSalaries();

    List<Salary> getSalaryByEmployeeId(Long employeeId);
}
