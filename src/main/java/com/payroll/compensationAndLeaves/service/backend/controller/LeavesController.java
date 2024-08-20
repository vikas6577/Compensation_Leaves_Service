package com.payroll.compensationAndLeaves.service.backend.controller;
import com.payroll.compensationAndLeaves.service.backend.dto.LeaveConfirmDto;
import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.dto.LeaveRequestDto;
import com.payroll.compensationAndLeaves.service.backend.dto.LeavesCountDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;
import com.payroll.compensationAndLeaves.service.backend.enums.LeaveStatus;
import com.payroll.compensationAndLeaves.service.backend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/leaves")
public class LeavesController {

    @Autowired
    private LeaveService leaveService;

    // Method to create leaves for an employee
    @PostMapping("/{id}")
    public ResponseEntity<Void> createLeaves(@PathVariable("id") Long employee_id)
    {
        boolean leaves = leaveService.createLeaves(employee_id);
        String response;
        if (leaves) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Method to update leaves
//    @PutMapping("/update")
//    //@PreAuthorize("hasRole('MANAGER')")
//    public ResponseEntity<String> updateLeave(@RequestBody LeaveDto leaveRequest) {
//        leaveService.updateLeave(leaveRequest);
//        return ResponseEntity.ok("Leave updated successfully");
//    }

    @GetMapping
    public ResponseEntity<List<LeavesCountDto>> getAllEmployeeLeaves() {
        List<LeavesCountDto> leaves = leaveService.getAllEmployeeLeaves();
        return ResponseEntity.ok(leaves);
    }

    // Method to get leaves for a specific employee by their ID
    @GetMapping("/{empId}")
    public ResponseEntity<List<Leaves>> getLeavesOfEmployee(@PathVariable("empId") Long empId) {
        List<Leaves> empLeaveList = leaveService.getLeavesOfEmployee(empId);
        return ResponseEntity.ok(empLeaveList);
    }

    // Method to get leaves for employees managed by a specific manager
//    @GetMapping("/getleaveemp/{managerId}")
//    public ResponseEntity<?> getLeavesOfEmployeeUnderManager(@PathVariable("managerId") Long managerId) {
//        List<List<LeavesTransaction>> empLeaveListManager = leaveService.getLeavesOfEmployeeUnderManager(managerId);
//        return ResponseEntity.ok(empLeaveListManager);
//    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<List<LeavesTransaction>> showLeaves(@PathVariable Long id) {
        List<LeavesTransaction> leavesTransaction = leaveService.showLeaves(id);
        return new ResponseEntity<>(leavesTransaction, HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Service is up", HttpStatus.OK);
    }

    @PostMapping("/{leaveId}/approveOrDisapprove")
    public ResponseEntity<Map<String, String>> approveOrDisapproveLeave(
            @PathVariable Long leaveId,
            @RequestBody LeaveConfirmDto leaveConfirmDto) {

        // Debugging
        System.out.println("Received LeaveConfirmDto: " + leaveConfirmDto);

        try {
            leaveService.approveOrDisapproveLeave(leaveId, leaveConfirmDto);
            String message = leaveConfirmDto.isApproved() ? "Leave approved successfully" : "Leave disapproved successfully";
            return ResponseEntity.ok(Collections.singletonMap("message", message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/raiseLeave")
    public ResponseEntity<Void> raiseLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto){
        leaveService.raiseLeaveRequest(leaveRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
