package com.employee.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.management.dao.EmployeeDetailsDao;
import com.employee.management.dto.EmployeeDetails;

@Service
public class EmployeeDetailsService {

    @Autowired
    private EmployeeDetailsDao dao;

    // =========================
    // SAVE EMPLOYEE
    // =========================
    public ResponseEntity<Object> saveEmployee(EmployeeDetails employee) {
        EmployeeDetails savedEmployee = dao.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // =========================
    // FETCH BY EMP ID
    // =========================
    public ResponseEntity<Object> getByEmpId(String empId) {
        EmployeeDetails employee = dao.getByEmpId(empId).orElse(null);

        if (employee == null) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // =========================
    // DELETE BY EMP ID
    // =========================
    public ResponseEntity<Object> deleteByEmpId(String empId) {
        EmployeeDetails employee = dao.getByEmpId(empId).orElse(null);

        if (employee == null) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }

        dao.deleteByEmpId(empId);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }

    // =========================
    // UPDATE BY EMP ID
    // =========================
    public ResponseEntity<Object> updateByEmpId(String empId, EmployeeDetails updatedEmp) {
        EmployeeDetails existingEmp = dao.getByEmpId(empId).orElse(null);

        if (existingEmp == null) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }

        existingEmp.setemployeeName(updatedEmp.getemployeeName());
        existingEmp.setEmpDesignation(updatedEmp.getEmpDesignation());
        existingEmp.setEmpDept(updatedEmp.getEmpDept());
        existingEmp.setEmpEmail(updatedEmp.getEmpEmail());
        existingEmp.setEmpAddress(updatedEmp.getEmpAddress());
        existingEmp.setEmpGender(updatedEmp.getEmpGender());
        existingEmp.setJoiningDate(updatedEmp.getJoiningDate());
        existingEmp.setEmpSalary(updatedEmp.getEmpSalary());

        EmployeeDetails updatedEmployee = dao.saveEmployee(existingEmp);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}
