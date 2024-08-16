package com.payroll.compensationAndLeaves.service.backend.controller;
import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeavesController {

    @Autowired
    private LeaveService leaveService;

    // Method to create leaves for an employee
    @PostMapping
    public ResponseEntity<String> createLeaves(@RequestBody Long employee_id)
    {
        boolean leaves = leaveService.createLeaves(employee_id);
        String response;
        if (leaves) {
            response = "Leaves added";
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response = "Error in adding leaves";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Method to update leaves
    @PutMapping("/update")
    //@PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> updateLeave(@RequestBody LeaveDto leaveRequest) {
        leaveService.updateLeave(leaveRequest);
        return ResponseEntity.ok("Leave updated successfully");
    }

    // Method to get all leaves
    @GetMapping("/getall")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Leaves>> getAllLeaves() {
        List<Leaves> leaves = leaveService.getAllLeaves();
        return ResponseEntity.ok(leaves);
    }

    // Method to get leaves for a specific employee by their ID
    @GetMapping("/getleave/{empId}")
    public ResponseEntity<List<Leaves>> getLeaves(@PathVariable("empId") Long empId) {
        List<Leaves> empLeaveList = leaveService.getLeaves(empId);
        return ResponseEntity.ok(empLeaveList);
    }

    // Method to get leaves for employees managed by a specific manager
    @GetMapping("/getleaveemp/{managerId}")
    public ResponseEntity<List<List<Leaves>>> getLeavesManager(@PathVariable("managerId") Long managerId) {
        List<List<Leaves>> empLeaveListManager = leaveService.getLeavesManager(managerId);
        return ResponseEntity.ok(empLeaveListManager);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        // Here, you can add custom logic to check the health of your service
        // For now, it just returns "Service is up" if the service is running
        return new ResponseEntity<>("Service is up", HttpStatus.OK);
    }
}
