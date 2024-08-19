package com.payroll.compensationAndLeaves.service.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LeavesCountDto {
    private Long employeeId;
    private Long currentLeaves;
    private Long totalLeaves;

}
