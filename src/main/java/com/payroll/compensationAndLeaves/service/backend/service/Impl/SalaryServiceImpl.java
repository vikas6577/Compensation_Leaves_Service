package com.payroll.compensationAndLeaves.service.backend.service.Impl;

import com.payroll.compensationAndLeaves.service.backend.entity.Salary;
import com.payroll.compensationAndLeaves.service.backend.repository.SalaryRepository;
import com.payroll.compensationAndLeaves.service.backend.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    public Salary insertSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public Salary updateSalary(Long id, Salary updatedSalary) {
        Salary existingSalary = salaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("salary not found with id : " + id));
        existingSalary.setSalary(updatedSalary.getSalary());
        return salaryRepository.save(existingSalary);
    }

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    public List<Salary> getSalaryByEmployeeId(Long employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }
}
