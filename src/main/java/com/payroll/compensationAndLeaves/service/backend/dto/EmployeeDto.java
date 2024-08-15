package com.payroll.compensationAndLeaves.service.backend.dto;


import com.payroll.compensationAndLeaves.service.backend.enums.Designation;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
        private String firstName;
        private String lastName;
        private String phone;
        private LocalDate birthDate;
        private Long reportsTo;
        private Long employeeId;
        private String email;
        private Designation role;

        public Long getEmployeeId() {
                return employeeId;
        }
}

