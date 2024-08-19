package com.payroll.compensationAndLeaves.service.backend.repository;

import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeavesTransactionRepository extends JpaRepository<Leaves,Long> {
//    List<LeavesTransaction> findAllByEmployeeId(Long employeeId);
}
