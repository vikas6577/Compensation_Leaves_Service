package com.payroll.compensationAndLeaves.service.backend.service;

import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;

import java.util.List;


public interface LeaveService {
//    void updateLeave(LeaveDto leaveDto);

    //void updateLeave(Long leaveId, LeaveRequest leaveRequest);

    List<Leaves> getAllEmployeeLeaves();


    List<Leaves> getLeavesOfEmployee(Long empId);

//    List<Leaves> getLeavesOfEmployeeUnderManager(Long managerId);


    boolean createLeaves(Long employeeId);


}
