package com.payroll.compensationAndLeaves.service.backend.repository;
import java.util.List;

import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves,Long> {

//    @Query(value = "select * from Leaves where employee_id=:empId",nativeQuery = true)
      List<Leaves> findByemployeeId(Long employeeId);

}
