package com.employee.management.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.management.dao.ClockInDao;
import com.employee.management.dto.ClockInDto;
import com.employee.management.repo.EmployeeDetailsRepo;
@Service
public class ClockInService {

    @Autowired
    private ClockInDao clockInDao;

    @Autowired
    private EmployeeDetailsRepo employeeRepo;

    public ResponseEntity<Object> clockIn(String empId, String location) {

        // ✅ Check employee exists
        if (!employeeRepo.findByEmpId(empId).isPresent()) {
            return new ResponseEntity<>("Employee not found. Cannot clock in.",
                    HttpStatus.NOT_FOUND);
        }

        // ❌ Prevent multiple clock-ins in same day
        if (clockInDao.getTodayClockIn(empId).isPresent()) {
            return new ResponseEntity<>("Employee already clocked in today.",
                    HttpStatus.BAD_REQUEST);
        }

        ClockInDto clockIn = new ClockInDto();
        clockIn.setEmpId(empId);
        clockIn.setLocation(location);
        clockIn.setClockInDate(LocalDate.now());
        clockIn.setClockInTime(LocalTime.now());

        return new ResponseEntity<>(clockInDao.saveClockIn(clockIn),
                HttpStatus.CREATED);
    }
}
