package com.payroll.compensationAndLeaves.service.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name ="employee_leaves_details"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leaves {

    @Id
    @Column(name = "leave_id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long leaveId;

    @Column(
            name = "employee_id",
            nullable = false
    )
    private Long employeeId;

    @Column(
            name = "leave_type",
            nullable = false
    )
    private String leaveType; // e.g., "sick", "vacation"

    @Column(name = "reason")
    private String reason;

//    @Column(name = "date",
//             nullable = false)
//    private String date;
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
