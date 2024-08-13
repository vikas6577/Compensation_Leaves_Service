package com.payroll.compensationAndLeaves.service.backend.service;

import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;

import java.util.List;


public interface LeaveService {
    void updateLeave(LeaveDto leaveRequest);

    //void updateLeave(Long leaveId, LeaveRequest leaveRequest);

    List<Leaves> getAllLeaves();


    List<Leaves> getLeaves(Long empId);
}
