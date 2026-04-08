package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.management.dto.EmployeeDetails;
import com.employee.management.service.EmployeeDetailsService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService service;

    // SAVE
    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDetails employee) {
        return service.saveEmployee(employee);
    }
    
    // FETCH BY EMP ID
    @GetMapping("fetchByEmpId/{empId}")
    public ResponseEntity<?> getByEmpId(@PathVariable String empId) {
        return service.getByEmpId(empId);
    }
    
    // UPDATE BY EMP ID
    @PutMapping("updateByEmpId/{empId}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable String empId,
            @RequestBody EmployeeDetails employee) {
        return service.updateByEmpId(empId, employee);
    }

    // DELETE BY EMP ID
    @DeleteMapping("deleteByEmpId/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String empId) {
        return service.deleteByEmpId(empId);
    }
}
