package com.payroll.compensationAndLeaves.service.backend.repository;


import com.payroll.compensationAndLeaves.service.backend.entity.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves,Long> {
}
