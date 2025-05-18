package com.noom.interview.sleep.api.response;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

public class SleepAveragesResponse {

    private  LocalDate startDate;
    private LocalDate endDate;
    private final double avgTimeInBedHours;
    private final LocalTime avgBedTime;
    private final LocalTime avgWakeUpTime;

    public SleepAveragesResponse(LocalDate startDate, LocalDate endDate,
                                 double avgTimeInBedHours,
                                 LocalTime avgBedTime,
                                 LocalTime avgWakeUpTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.avgTimeInBedHours = BigDecimal.valueOf(avgTimeInBedHours)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        this.avgBedTime = avgBedTime;
        this.avgWakeUpTime = avgWakeUpTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getAvgWakeUpTime() {
        return avgWakeUpTime;
    }

    public LocalTime getAvgBedTime() {
        return avgBedTime;
    }

    public double getAvgTimeInBedHours() {
        return avgTimeInBedHours;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
