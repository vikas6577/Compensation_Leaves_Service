package com.payroll.compensationAndLeaves.service.backend.mapper;

import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;

public class LeavesMapper {

    public static LeavesTransaction mapToLeaves(LeaveDto leaveDto){
        LeavesTransaction leaves = new LeavesTransaction();
        leaves.setEmployeeId(leaveDto.getEmployeeId());
        leaves.setReason(leaveDto.getReason());
        return leaves;
    }

}
