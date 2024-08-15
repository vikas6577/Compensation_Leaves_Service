package com.payroll.compensationAndLeaves.service.backend.service.impl;

import com.payroll.compensationAndLeaves.service.backend.dto.EmployeeDto;
import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.mapper.LeavesMapper;
import com.payroll.compensationAndLeaves.service.backend.repository.LeavesRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    private Logger logger= LoggerFactory.getLogger(LeaveServiceImpl.class);

    @Autowired
    private LeavesRepository leavesRepository;

    @Autowired
    private RestTemplate restTemplate;

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
        System.out.println("EmployeeId:"+empId);
        return leavesRepository.findByemployeeId(empId);
    }

    @Override
    public  List<List<Leaves>> getLeavesManager(Long managerId){
        // getting list of all employees whom reportsTo field is equal to managerId
        //url: http:localhost:8080/api/v1/employee/{managerId}/employees

        List<List<Leaves>> response = new ArrayList<>();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "123");

        String url="http://localhost:8080/api/v1/employee/"+managerId+"/employees";

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<List<EmployeeDto>> employeeDtoArrayList = restTemplate.exchange(
                                                                url,
                                                                HttpMethod.GET,
                                                                request,
                new ParameterizedTypeReference<List<EmployeeDto>>() {}
                                                                );

        List<EmployeeDto> employeeArray = employeeDtoArrayList.getBody();


        for(EmployeeDto it : employeeArray) {
            List<Leaves> leaves = getLeaves(it.getEmployeeId());
            // Add the leaves list to the response
            response.add(leaves);
        }
        return response;
    }
}
