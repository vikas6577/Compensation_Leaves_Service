package com.payroll.compensationAndLeaves.service.backend.service.impl;

import com.payroll.compensationAndLeaves.service.backend.dto.SalaryDto;
import com.payroll.compensationAndLeaves.service.backend.entity.SalaryEntity;
import com.payroll.compensationAndLeaves.service.backend.repository.SalaryRepository;
import com.payroll.compensationAndLeaves.service.backend.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    // We have to call insert salary from createemployee so that the entry of the same employe with salary is done in this table (feign client api call to insert the same employee id)
    public boolean insertSalary(SalaryDto salaryDto) {
        try {
            SalaryEntity salary = SalaryEntity.builder()
                    .employeeId(salaryDto.getEmployeeId())
                    .salary(salaryDto.getSalary())
                    .build();
            SalaryEntity savedSalary = salaryRepository.save(salary);
            return savedSalary != null && savedSalary.getId() != null;
        }
        catch (Exception ex){
            throw new RuntimeException("An unexpected error occurred while inserting salary. Please try again later.", ex);
        }
    }
    public boolean updateSalary(Long id, SalaryDto salarydto) {
         try {
             SalaryEntity existingSalary = salaryRepository.findById(id)
                     .orElseThrow(() -> new RuntimeException("salary not found with id : " + id));
             existingSalary.setSalary(salarydto.getSalary());
             SalaryEntity updatedSalary=  salaryRepository.save(existingSalary);
             return updatedSalary!=null && updatedSalary.getId()!=null;
         }
         catch(Exception ex){
             throw new RuntimeException("An unexpected error occurred while updating salary. Please try again later.", ex);
         }
    }

    public List<SalaryDto> getAllSalaries() {
        List<SalaryEntity> salaries = salaryRepository.findAll();

        // Convert SalaryEntity list to SalaryDto list using builder pattern
        List<SalaryDto> salariesDto = salaries.stream()
                .map(salary -> SalaryDto.builder()
                        .employeeId(salary.getEmployeeId())
                        .salary(salary.getSalary())
                        .build())
                .collect(Collectors.toList());

        return salariesDto;

    }

    public List<SalaryEntity> getSalaryByEmployeeId(Long employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }
    private SalaryDto convertToDto(SalaryEntity salary) {
        SalaryDto salaryDto= new SalaryDto();
        salaryDto.setEmployeeId(salary.getEmployeeId());
        salaryDto.setSalary(salary.getSalary());
        return salaryDto;
    }
}
