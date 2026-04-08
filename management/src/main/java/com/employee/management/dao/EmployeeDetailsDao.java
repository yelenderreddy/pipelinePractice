package com.employee.management.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.management.dto.EmployeeDetails;
import com.employee.management.repo.EmployeeDetailsRepo;

@Repository
public class EmployeeDetailsDao {

    @Autowired
    private EmployeeDetailsRepo repo;

    public EmployeeDetails saveEmployee(EmployeeDetails employee) {
        return repo.save(employee);
    }

    public Optional<EmployeeDetails> getByEmpId(String empId) {
        return repo.findByEmpId(empId);
    }

    public void deleteByEmpId(String empId) {
        repo.deleteByEmpId(empId);
    }
}
