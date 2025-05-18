package com.noom.interview.sleep.domain;

import com.noom.interview.sleep.enums.SleepFeeling;

import java.time.*;
import java.util.UUID;


public class Sleep {
    private String id;
    private LocalDate date;
    private LocalDateTime timeInBedStart;
    private LocalDateTime timeInBedEnd;
    private Duration totalTimeInBed;
    private SleepFeeling feeling;
    private Instant createdAt;
    private Instant updatedAt;

    public Sleep(String id, LocalDate date, LocalDateTime timeInBedStart, LocalDateTime timeInBedEnd,
                 Duration totalTimeInBed, SleepFeeling feeling, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.date = date;
        this.timeInBedStart = timeInBedStart;
        this.timeInBedEnd = timeInBedEnd;
        this.totalTimeInBed = totalTimeInBed;
        this.feeling = feeling;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Sleep() {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        final var currentDate = LocalDate.now();
        final var startTimeInBed = LocalDateTime.now();

        this.id = id;
        this.date = currentDate;
        this.timeInBedStart = startTimeInBed;
        this.timeInBedEnd = null;
        this.totalTimeInBed = null;
        this.feeling = null;
        this.createdAt = now;
        this.updatedAt = now;
    }

    public void updateSleep(Sleep sleep) {
        this.timeInBedEnd =  LocalDateTime.now();
        this.totalTimeInBed = Duration.between(this.timeInBedStart, this.timeInBedEnd);
        this.updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getTimeInBedStart() {
        return timeInBedStart;
    }

    public LocalDateTime getTimeInBedEnd() {
        return timeInBedEnd;
    }

    public Duration getTotalTimeInBed() {
        return totalTimeInBed;
    }

    public SleepFeeling getFeeling() {
        return feeling;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setMorningFeeling(String morningFeeling) {
        this.feeling = SleepFeeling.valueOf(morningFeeling.toUpperCase());
    }
}
