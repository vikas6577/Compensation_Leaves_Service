package com.payroll.compensationAndLeaves.service.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDto {
    private Long employeeId;
    private String reason;
   // private String date ;

}
