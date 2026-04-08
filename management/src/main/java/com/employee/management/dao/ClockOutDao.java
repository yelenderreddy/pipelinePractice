package com.employee.management.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.management.dto.ClockOutDto;
import com.employee.management.repo.ClockOutRepo;

@Repository
public class ClockOutDao {

    @Autowired
    private ClockOutRepo repo;

    public ClockOutDto saveClockOut(ClockOutDto clockOut) {
        return repo.save(clockOut);
    }
    
    public List<ClockOutDto> getClockOutsBetweenDates(
            String empId,
            LocalDate startDate,
            LocalDate endDate) {

        return repo.findByEmpIdAndClockOutDateBetween(empId, startDate, endDate);
    }

}

