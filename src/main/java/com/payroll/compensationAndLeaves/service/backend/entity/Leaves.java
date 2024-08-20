package com.payroll.compensationAndLeaves.service.backend.entity;

import com.payroll.compensationAndLeaves.service.backend.enums.LeaveStatus;
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
    @GeneratedValue(
            strategy=GenerationType.IDENTITY
    )
    private Long Id;


    @Column (
            name="employee_id",
            nullable=false
    )
    private Long employeeId;

    @Column(
            name="current_leaves",
            nullable=false
    )
    private Long currentLeaves;


    @Column(
            name="total_leaves",
            nullable=false
    )
    private Long totalLeaves;

}

