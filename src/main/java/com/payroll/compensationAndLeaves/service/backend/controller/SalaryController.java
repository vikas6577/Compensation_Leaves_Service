package com.payroll.compensationAndLeaves.service.backend.controller;

import com.payroll.compensationAndLeaves.service.backend.dto.SalaryDto;
import com.payroll.compensationAndLeaves.service.backend.entity.SalaryEntity;
import com.payroll.compensationAndLeaves.service.backend.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> insertSalary(@RequestBody SalaryDto salaryDto) {
        Map<String,Object> response=new HashMap<>();
        boolean salary= salaryService.insertSalary(salaryDto);
        if(salary==true) {
            response.put("message", "Salary Added");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("error","Error in saving the salary");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,Object>> updateSalary(@PathVariable Long id, @RequestBody SalaryDto salaryDto) {
        Map<String,Object> response=new HashMap<>();
        boolean updatedSalary=salaryService.updateSalary(id,salaryDto);
        if(updatedSalary==true) {
            response.put("message", "Salary Added");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("error","Error in saving the salary");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    //@PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")
    public ResponseEntity<Map<String,Object>> getAllSalaries() {
        List<SalaryEntity>salaries= salaryService.getAllSalaries();
        Map<String,Object> response=new HashMap<>();
        response.put("Data",salaries);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/{employeeId}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'ACCOUNTANT')")
    public ResponseEntity<Map<String,Object>> getSalaryByEmployeeId(@PathVariable Long employeeId) {
        List<SalaryEntity> salary = salaryService.getSalaryByEmployeeId(employeeId);
        Map<String,Object> response= new HashMap<>();
        response.put("Salary",salary);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
}
