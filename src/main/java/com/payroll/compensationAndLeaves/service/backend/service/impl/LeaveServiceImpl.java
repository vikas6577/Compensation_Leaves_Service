package com.payroll.compensationAndLeaves.service.backend.service.impl;

import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.mapper.LeavesMapper;
import com.payroll.compensationAndLeaves.service.backend.repository.LeavesRepository;
import com.payroll.compensationAndLeaves.service.backend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeavesRepository leavesRepository;

    @Override
    public void updateLeave(LeaveDto leaveRequest) {
        Leaves leaves = LeavesMapper.mapToLeaves(leaveRequest);
        leavesRepository.save(leaves);
    }

    @Override
    public List<Leaves> getAllLeaves() {

        return leavesRepository.findAll();

    }

    @Override
    public List<Leaves> getLeaves(Long empId){
        return leavesRepository.findByemployeeId(empId);
    }
}
