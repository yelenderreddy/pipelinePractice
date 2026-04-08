package com.employee.management.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.management.dao.ClockInDao;
import com.employee.management.dao.ClockOutDao;
import com.employee.management.dto.AttendanceDto;
import com.employee.management.dto.ClockInDto;
import com.employee.management.dto.ClockOutDto;
import com.employee.management.repo.EmployeeDetailsRepo;

@Service
public class AttendanceService {

    @Autowired
    private ClockInDao clockInDao;

    @Autowired
    private ClockOutDao clockOutDao;

    @Autowired
    private EmployeeDetailsRepo employeeRepo;

    public ResponseEntity<Object> getAttendance(
            String empId,
            LocalDate startDate,
            LocalDate endDate) {

        if (!employeeRepo.findByEmpId(empId).isPresent()) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }

        List<ClockInDto> clockIns =
                clockInDao.getClockInsBetweenDates(empId, startDate, endDate);

        List<ClockOutDto> clockOuts =
                clockOutDao.getClockOutsBetweenDates(empId, startDate, endDate);

        Map<LocalDate, AttendanceDto> attendanceMap = new HashMap<>();

        // 🟢 Process clock-ins
        for (ClockInDto in : clockIns) {
            AttendanceDto dto = new AttendanceDto();
            dto.setDate(in.getClockInDate());
            dto.setClockInTime(in.getClockInTime());
            dto.setClockInLocation(in.getLocation());
            attendanceMap.put(in.getClockInDate(), dto);
        }

        // 🔵 Process clock-outs
        for (ClockOutDto out : clockOuts) {
            AttendanceDto dto =
                    attendanceMap.getOrDefault(out.getClockOutDate(), new AttendanceDto());

            dto.setDate(out.getClockOutDate());
            dto.setClockOutTime(out.getClockOutTime());
            dto.setClockOutLocation(out.getLocation());

            // ⏱ Calculate worked hours if clock-in exists
            if (dto.getClockInTime() != null) {
                LocalDateTime inTime =
                        LocalDateTime.of(dto.getDate(), dto.getClockInTime());
                LocalDateTime outTime =
                        LocalDateTime.of(dto.getDate(), dto.getClockOutTime());

                long hours = Duration.between(inTime, outTime).toHours();
                dto.setWorkedHours(hours);
            }

            attendanceMap.put(out.getClockOutDate(), dto);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("empId", empId);
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("attendance", new ArrayList<>(attendanceMap.values()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
