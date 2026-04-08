package com.employee.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.management.dto.LoginRequest;
import com.employee.management.repo.EmployeeDetailsRepo;
import com.employee.management.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private EmployeeDetailsRepo repo;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<String> login(LoginRequest request) {

        return repo.findByEmpEmail(request.getEmpEmail())
                .map(emp -> {
                    if (emp.getEmpPassword().equals(request.getEmpPassword())) {
                        String token = jwtUtil.generateToken(emp.getEmpId());
                        return new ResponseEntity<>(token, HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
                })
                .orElse(new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND));
    }
}
