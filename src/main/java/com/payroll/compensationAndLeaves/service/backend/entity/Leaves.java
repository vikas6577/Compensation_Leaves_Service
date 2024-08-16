package com.payroll.compensationAndLeaves.service.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (
    name="employee_leaves_count"
)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Leaves {
    @Id
    @Column(name="leave_id")
    @GeneratedValue(
            strategy=GenerationType.IDENTITY
    )
    private Long LeaveId;

    @Column (
            name="employee_id",
            nullable=false
    )
    private Long employeeId;

    @Column(
            name="total_leaves",
            nullable=false
    )
    private Long totalLeaves;

    @Column(
            name="current_leaves",
            nullable=false
    )
    private Long currentLeaves;
}

