package com.payroll.compensationAndLeaves.service.backend.controller;

import com.payroll.compensationAndLeaves.service.backend.entity.Salary;
import com.payroll.compensationAndLeaves.service.backend.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public Salary insertSalary(@RequestBody Salary salary) {
        return salaryService.insertSalary(salary);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Salary updateSalary(@PathVariable Long id, @RequestBody Salary salary) {
        return salaryService.updateSalary(id, salary);
    }

    @GetMapping
    //@PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @GetMapping("/{employeeId}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'ACCOUNTANT')")
    public List<Salary> getSalaryByEmployeeId(@PathVariable Long employeeId) {
        return salaryService.getSalaryByEmployeeId(employeeId);
    }
}
