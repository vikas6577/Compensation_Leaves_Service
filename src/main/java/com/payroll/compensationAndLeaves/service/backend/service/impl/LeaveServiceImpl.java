package com.payroll.compensationAndLeaves.service.backend.service.impl;

import com.payroll.compensationAndLeaves.service.backend.dto.EmployeeDto;
import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    private Logger logger= LoggerFactory.getLogger(LeaveServiceImpl.class);

    @Autowired
    private LeavesRepository leavesRepository;

    @Autowired
    private LeavesTransactionRepository leavesTransactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Leaves> getAllEmployeeLeaves() {
        List<Leaves> employeeLeaves =leavesRepository.findAll();
        return employeeLeaves;
    }

    @Override
    public List<Leaves> getLeavesOfEmployee(Long empId){
        System.out.println("EmployeeId:"+empId);
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
    public boolean createLeaves(Long employeeId){
           Leaves leave= new Leaves();
           leave.setEmployeeId(employeeId);
           leave.setCurrentLeaves(40L);
           leave.setTotalLeaves(40L);
           leavesRepository.save(leave);
           return true;
    }


//    @Override
//    public void updateLeave(LeaveDto leaveDto)
//    {
//        Leave leave=leavesRepository.findByemployeeId(leaveDto.getEmployeeId());
//
//
//
//    }
}
