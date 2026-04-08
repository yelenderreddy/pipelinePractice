package com.employee.management.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.dto.EmployeeDetails;

import jakarta.transaction.Transactional;

public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Integer> {

    Optional<EmployeeDetails> findByEmpId(String empId);
    
    Optional<EmployeeDetails> findByEmpEmail(String empEmail);

    @Transactional
    Optional<EmployeeDetails> deleteByEmpId(String empId);
    
}
