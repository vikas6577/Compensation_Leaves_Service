package com.payroll.compensationAndLeaves.service.backend.service;

import com.payroll.compensationAndLeaves.service.backend.dto.LeaveConfirmDto;
import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.dto.LeaveRequestDto;
import com.payroll.compensationAndLeaves.service.backend.dto.LeavesCountDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;
import com.payroll.compensationAndLeaves.service.backend.enums.LeaveStatus;

import java.util.List;


public interface LeaveService {
//    void updateLeave(LeaveDto leaveDto);

    //void updateLeave(Long leaveId, LeaveRequest leaveRequest);

    List<LeavesCountDto> getAllEmployeeLeaves();


    List<Leaves> getLeavesOfEmployee(Long empId);

//    List<Leaves> getLeavesOfEmployeeUnderManager(Long managerId);


    boolean createLeaves(Long employeeId);

    List<LeavesTransaction> showLeaves(Long managerId);


    void approveOrDisapproveLeave(Long leaveTransactionId, LeaveConfirmDto leaveConfirmDto);

    void raiseLeaveRequest(LeaveRequestDto leaveRequestDto);
}
