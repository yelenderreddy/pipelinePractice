package com.employee.management.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.management.dto.ClockInDto;
import com.employee.management.repo.ClockInRepo;
@Repository
public class ClockInDao {

    @Autowired
    private ClockInRepo repo;

    public ClockInDto saveClockIn(ClockInDto clockIn) {
        return repo.save(clockIn);
    }

    public Optional<ClockInDto> getLatestClockIn(String empId) {
        return repo.findTopByEmpIdOrderByClockInDateDescClockInTimeDesc(empId);
    }

    public Optional<ClockInDto> getTodayClockIn(String empId) {
        return repo.findByEmpIdAndClockInDate(empId, LocalDate.now());
    }
    public List<ClockInDto> getClockInsBetweenDates(
            String empId,
            LocalDate startDate,
            LocalDate endDate) {

        return repo.findByEmpIdAndClockInDateBetween(empId, startDate, endDate);
    }

}
