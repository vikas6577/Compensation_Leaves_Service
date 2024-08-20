package com.payroll.compensationAndLeaves.service.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveConfirmDto{
    @JsonProperty
    private boolean isApproved;

    private Long leaveCount;
}
