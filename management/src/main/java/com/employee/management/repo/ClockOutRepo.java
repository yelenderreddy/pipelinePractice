package com.employee.management.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.management.dto.ClockOutDto;

public interface ClockOutRepo extends JpaRepository<ClockOutDto, Integer> {
	
	  List<ClockOutDto> findByEmpIdAndClockOutDateBetween(
	            String empId,
	            LocalDate startDate,
	            LocalDate endDate
	    );
}
