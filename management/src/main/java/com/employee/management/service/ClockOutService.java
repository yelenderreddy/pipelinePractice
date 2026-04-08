package com.employee.management.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.management.dao.ClockInDao;
import com.employee.management.dao.ClockOutDao;
import com.employee.management.dto.ClockInDto;
import com.employee.management.dto.ClockOutDto;
import com.employee.management.repo.EmployeeDetailsRepo;
import java.time.Duration; 
@Service
public class ClockOutService {

    @Autowired
    private ClockOutDao clockOutDao;

    @Autowired
    private ClockInDao clockInDao;

    @Autowired
    private EmployeeDetailsRepo employeeRepo;

    public ResponseEntity<Object> clockOut(String empId, String location) {

        // ✅ Check employee exists
        if (!employeeRepo.findByEmpId(empId).isPresent()) {
            return new ResponseEntity<>("Employee not found. Cannot clock out.",
                    HttpStatus.NOT_FOUND);
        }

        // ✅ Check clock-in exists
        ClockInDto clockIn = clockInDao.getLatestClockIn(empId).orElse(null);

        if (clockIn == null) {
            return new ResponseEntity<>("Employee has not clocked in.",
                    HttpStatus.BAD_REQUEST);
        }

        // ⏱ Calculate 6 hour gap
        LocalDateTime clockInDateTime =
                LocalDateTime.of(clockIn.getClockInDate(), clockIn.getClockInTime());

        LocalDateTime now = LocalDateTime.now();

        long hoursWorked = Duration.between(clockInDateTime, now).toHours();

        if (hoursWorked < 6) {
            return new ResponseEntity<>(
                    "Clock-out allowed only after 6 hours. Worked hours: " + hoursWorked,
                    HttpStatus.BAD_REQUEST);
        }

        ClockOutDto clockOut = new ClockOutDto();
        clockOut.setEmpId(empId);
        clockOut.setLocation(location);
        clockOut.setClockOutDate(LocalDate.now());
        clockOut.setClockOutTime(LocalTime.now());

        return new ResponseEntity<>(clockOutDao.saveClockOut(clockOut),
                HttpStatus.CREATED);
    }
}
