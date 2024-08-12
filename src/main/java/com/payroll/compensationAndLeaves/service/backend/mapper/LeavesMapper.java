package com.payroll.compensationAndLeaves.service.backend.mapper;

import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;

public class LeavesMapper {

    public static Leaves mapToLeaves(LeaveDto leaveDto){
        Leaves leaves = new Leaves();
        leaves.setLeaveType(leaveDto.getLeaveType());
        leaves.setEmployeeId(leaveDto.getEmployeeId());
        leaves.setReason(leaveDto.getReason());
        return leaves;
    }

}
