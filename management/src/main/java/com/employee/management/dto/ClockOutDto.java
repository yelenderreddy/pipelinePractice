package com.employee.management.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClockOutDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String empId;
    private String location;
    private LocalDate clockOutDate;
    private LocalTime clockOutTime;

    // ===== Getters & Setters =====

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getClockOutDate() {
        return clockOutDate;
    }
    public void setClockOutDate(LocalDate clockOutDate) {
        this.clockOutDate = clockOutDate;
    }

    public LocalTime getClockOutTime() {
        return clockOutTime;
    }
    public void setClockOutTime(LocalTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }
}
