package com.employee.management.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceDto {

    private LocalDate date;
    private LocalTime clockInTime;
    private String clockInLocation;
    private LocalTime clockOutTime;
    private String clockOutLocation;
    private long workedHours;

    // Getters & Setters

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getClockInTime() {
        return clockInTime;
    }
    public void setClockInTime(LocalTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getClockInLocation() {
        return clockInLocation;
    }
    public void setClockInLocation(String clockInLocation) {
        this.clockInLocation = clockInLocation;
    }

    public LocalTime getClockOutTime() {
        return clockOutTime;
    }
    public void setClockOutTime(LocalTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getClockOutLocation() {
        return clockOutLocation;
    }
    public void setClockOutLocation(String clockOutLocation) {
        this.clockOutLocation = clockOutLocation;
    }

    public long getWorkedHours() {
        return workedHours;
    }
    public void setWorkedHours(long workedHours) {
        this.workedHours = workedHours;
    }
}
