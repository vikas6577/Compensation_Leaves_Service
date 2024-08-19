package com.payroll.compensationAndLeaves.service.backend.service;


import com.payroll.compensationAndLeaves.service.backend.dto.SalaryDto;
import com.payroll.compensationAndLeaves.service.backend.entity.SalaryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface SalaryService{
    boolean insertSalary(SalaryDto salary);

    boolean updateSalary(Long id, SalaryDto salarydto);

    List<SalaryDto> getAllSalaries();

    List<SalaryEntity> getSalaryByEmployeeId(Long employeeId);
}
