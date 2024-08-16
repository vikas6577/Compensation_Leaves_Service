package com.payroll.compensationAndLeaves.service.backend.entity;

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
public class Leaves {

    @Id
    @Column(name = "leave_id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long leaveId;
//leaveid , employeeid, reason, requesteddate, aprroveddate, leavescount
    @Column(
            name = "employee_id",
            nullable = false
    )
    private Long employeeId;

//    @Column(
//            name = "leave_type",
//            nullable = false
//    )
//    private String leaveType;

    @Column(name = "reason")
    private String reason;

    @Column (name="requested_date")
    private LocalDate requestedDate;

    @Column (name="approved_date")
    private LocalDate approvedDate;

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
}


// total leaves, current leaves in the currentr tabole for adding
