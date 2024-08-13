package com.payroll.compensationAndLeaves.service.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private Long employeeId;
    private Long salary;
}
