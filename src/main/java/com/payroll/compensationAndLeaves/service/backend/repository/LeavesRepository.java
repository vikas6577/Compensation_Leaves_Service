package com.payroll.compensationAndLeaves.service.backend.repository;
import java.util.List;

import com.payroll.compensationAndLeaves.service.backend.entity.LeavesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavesRepository extends JpaRepository<LeavesTransaction,Long> {

//    @Query(value = "select * from Leaves where employee_id=:empId",nativeQuery = true)
      List<LeavesTransaction> findByemployeeId(Long employeeId);

}
