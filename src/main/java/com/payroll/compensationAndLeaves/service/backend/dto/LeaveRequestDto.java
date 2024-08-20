package com.payroll.compensationAndLeaves.service.backend.dto;

import com.payroll.compensationAndLeaves.service.backend.enums.LeaveStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LeaveRequestDto {
    private Long employeeId;
    private String reason;
    private Long reportsTo;
    private Long leavesCount;
}
