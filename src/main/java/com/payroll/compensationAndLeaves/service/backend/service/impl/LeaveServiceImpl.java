package com.payroll.compensationAndLeaves.service.backend.service.impl;

import com.payroll.compensationAndLeaves.service.backend.dto.*;
import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.enums.LeaveStatus;
import com.payroll.compensationAndLeaves.service.backend.mapper.LeavesMapper;
import com.payroll.compensationAndLeaves.service.backend.repository.LeavesRepository;
import com.payroll.compensationAndLeaves.service.backend.repository.LeavesTransactionRepository;
import com.payroll.compensationAndLeaves.service.backend.service.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    private Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);

    @Autowired
    private LeavesRepository leavesRepository;

    @Autowired
    private LeavesTransactionRepository leavesTransactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<LeavesCountDto> getAllEmployeeLeaves() {
        List<Leaves> employeeLeaves = leavesRepository.findAll();
        List<LeavesCountDto> leavesCountDtos = employeeLeaves.stream()
                .map(leaves -> LeavesCountDto.builder()
                        .employeeId(leaves.getEmployeeId())
                        .currentLeaves(leaves.getCurrentLeaves())
                        .totalLeaves(leaves.getTotalLeaves())
                        .build())
                .collect(Collectors.toList());

        return leavesCountDtos;
    }

    @Override
    public List<Leaves> getLeavesOfEmployee(Long empId) {
        System.out.println("EmployeeId:" + empId);
        return leavesRepository.findAllByEmployeeId(empId);
    }

//    @Override
//    public  List<Leaves> getLeavesOfEmployeeUnderManager(Long managerId){
//        List<Leaves> response = new ArrayList<>();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth("admin", "123");
//
//        String url="http://localhost:8080/api/v1/employee/"+managerId+"/employees";
//
//        HttpEntity request = new HttpEntity(headers);
//
//        ResponseEntity<List<EmployeeDto>> employeeDtoArrayList = restTemplate.exchange(
//                                                                url,
//                                                                HttpMethod.GET,
//                                                                request,
//                new ParameterizedTypeReference<List<EmployeeDto>>() {}
//                                                                );
//
//        List<EmployeeDto> employeeArray = employeeDtoArrayList.getBody();
//
//
//        for(EmployeeDto it : employeeArray) {
//            List<LeavesTransaction> leaves = getLeaves(it.getEmployeeId());
//            // Add the leaves list to the response
//            response.add(leaves);
//        }
//        return response;
//    }

    @Override
    public boolean createLeaves(Long employeeId) {
        Leaves leave = new Leaves();
        leave.setEmployeeId(employeeId);
        leave.setCurrentLeaves(40L);
        leave.setTotalLeaves(40L);
        leavesRepository.save(leave);
        return true;
    }

    @Override
    public List<LeavesTransaction> showLeaves(Long managerId) {
        List<LeavesTransaction> allLeaves = leavesTransactionRepository.findByReportsTo(managerId);

        // Filter the leaves to get only those with a status of PENDING
        List<LeavesTransaction> pendingLeaves = allLeaves.stream()
                .filter(leave -> leave.getStatus() == LeaveStatus.PENDING)
                .collect(Collectors.toList());

        return pendingLeaves;
    }

    @Override
    public void approveOrDisapproveLeave(Long leaveId, LeaveConfirmDto leaveConfirmDto) {
        Optional<LeavesTransaction> leavesTransaction = leavesTransactionRepository.findById(leaveId);

        LeavesTransaction currentLeave = leavesTransaction.orElseThrow(() -> new IllegalArgumentException("Leave is not present"));

        if (leaveConfirmDto.isApproved()) {
            currentLeave.setApprovedDate(LocalDate.now());
            currentLeave.setStatus(LeaveStatus.APPROVED);

            // Fetch the employee's leave count
            Optional<Leaves> leavesOptional = Optional.ofNullable(leavesRepository.findByemployeeId(currentLeave.getEmployeeId()));
            Leaves leaves = leavesOptional.orElseThrow(() -> new IllegalArgumentException("Leaves not found for employee ID: " + currentLeave.getEmployeeId()));
            if (leaves.getCurrentLeaves() > leaveConfirmDto.getLeaveCount()) {
                leaves.setCurrentLeaves(leaves.getCurrentLeaves() - leaveConfirmDto.getLeaveCount());
                leavesRepository.save(leaves);
            } else {
                throw new IllegalArgumentException("No more leaves available for employee ID: " + currentLeave.getEmployeeId());
            }
        } else {
            currentLeave.setStatus(LeaveStatus.REJECTED);
        }
        leavesTransactionRepository.save(currentLeave);
    }

    @Override
    public void raiseLeaveRequest(LeaveRequestDto leaveRequestDto) {
        LeavesTransaction leavesTransaction = new LeavesTransaction();
        leavesTransaction.setEmployeeId(leaveRequestDto.getEmployeeId());
        leavesTransaction.setReason(leaveRequestDto.getReason());
        leavesTransaction.setReportsTo(leaveRequestDto.getReportsTo());
        leavesTransaction.setLeavesCount(leaveRequestDto.getLeavesCount());
        leavesTransaction.setRequestedDate(LocalDate.now());
        leavesTransaction.setStatus(LeaveStatus.PENDING);
        leavesTransactionRepository.save(leavesTransaction);
    }
}