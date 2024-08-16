package com.payroll.compensationAndLeaves.service.backend.service;

import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;

import java.util.List;


public interface LeaveService {
    void updateLeave(LeaveDto leaveRequest);

    //void updateLeave(Long leaveId, LeaveRequest leaveRequest);

    List<LeavesTransaction> getAllLeaves();


    List<LeavesTransaction> getLeaves(Long empId);

    List<List<LeavesTransaction>> getLeavesManager(Long managerId);
    boolean createLeaves(Long employeeId);
}
