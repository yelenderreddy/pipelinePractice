package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.management.service.ClockOutService;

@RestController
@RequestMapping("/api/clockout")
public class ClockOutController {

    @Autowired
    private ClockOutService service;

    @PostMapping
    public ResponseEntity<?> clockOut(
            @RequestParam String empId,
            @RequestParam String location) {

        return service.clockOut(empId, location);
    }
}

