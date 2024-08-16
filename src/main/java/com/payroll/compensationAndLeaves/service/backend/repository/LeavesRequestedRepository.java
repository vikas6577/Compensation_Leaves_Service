package com.payroll.compensationAndLeaves.service.backend.repository;

import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeavesRequestedRepository extends JpaRepository<LeavesTransaction,Long> {

}
