package com.employee.management.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.management.dto.ClockInDto;

public interface ClockInRepo extends JpaRepository<ClockInDto, Integer> {

    Optional<ClockInDto> findTopByEmpIdOrderByClockInDateDescClockInTimeDesc(String empId);

    Optional<ClockInDto> findByEmpIdAndClockInDate(String empId, LocalDate clockInDate);
    
    List<ClockInDto> findByEmpIdAndClockInDateBetween(
            String empId,
            LocalDate startDate,
            LocalDate endDate
    );
}
