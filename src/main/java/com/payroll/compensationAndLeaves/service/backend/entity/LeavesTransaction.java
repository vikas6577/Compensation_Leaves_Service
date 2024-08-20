package com.payroll.compensationAndLeaves.service.backend.entity;

import com.payroll.compensationAndLeaves.service.backend.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
    name ="employee_leaves_details"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeavesTransaction {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;

    @Column(
            name = "employee_id",
            nullable = false
    )
    private Long employeeId;

    private String reason;

    @Column (name="requested_date",nullable = false)
    private LocalDate requestedDate;

    @Column (name="approved_date")
    private LocalDate approvedDate;

    @Column (name="leave_status")
    private LeaveStatus status;

    private Long reportsTo;

    @Column (name="leaves_count", nullable = false)
    private Long leavesCount;

}

