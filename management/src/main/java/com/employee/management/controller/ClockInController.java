package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.management.service.ClockInService;

@RestController
@RequestMapping("/api/clockin")
public class ClockInController {

    @Autowired
    private ClockInService service;

    @PostMapping
    public ResponseEntity<?> clockIn(
            @RequestParam String empId,
            @RequestParam String location) {

        return service.clockIn(empId, location);
    }
}
