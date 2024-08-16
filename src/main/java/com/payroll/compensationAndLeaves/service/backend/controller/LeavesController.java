package com.payroll.compensationAndLeaves.service.backend.controller;

import com.payroll.compensationAndLeaves.service.backend.dto.LeaveDto;
import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PUT /api/leaves/{leaveId}

@RestController
@RequestMapping("api/leaves/")
public class LeavesController {
    @Autowired
    private LeaveService leaveService;

//update leaves means mgr is adding the leave to db

    @PostMapping("/update")
    //@Preatherisation("hasRole('MANAGER')")
    public ResponseEntity<?> updateLeave(@RequestBody LeaveDto leaveRequest) {
        leaveService.updateLeave(leaveRequest);
        return ResponseEntity.ok("Leave updated successfully");
    }

    @GetMapping("/getall")
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Leaves>> getAllLeaves() {
        List<Leaves> leaves = leaveService.getAllLeaves();
        return ResponseEntity.ok(leaves);
    }

    // employeeId is passed as URL parameter
    @GetMapping("/getleave/{empId}")
    public ResponseEntity<List<Leaves>> getLeaves(@PathVariable("empId") Long empId){
            List<Leaves> emp_leave_list = leaveService.getLeaves(empId);
            return ResponseEntity.ok(emp_leave_list);
    }

    //
    @GetMapping("/getleaveemp/{managerId}")
    public ResponseEntity<List<List<Leaves>>> getLeavesManager(@PathVariable("managerId") Long managerId){
        List<List<Leaves>> emp_leave_list_manager = leaveService.getLeavesManager(managerId);
        return ResponseEntity.ok(emp_leave_list_manager);
    }

}
