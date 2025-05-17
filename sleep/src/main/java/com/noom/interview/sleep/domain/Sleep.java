package com.noom.interview.sleep.domain;

import com.noom.interview.sleep.enums.SleepFeeling;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


public class Sleep {
    private String id;
    private LocalDate date;
    private LocalTime timeInBedStart;
    private LocalTime timeInBedEnd;
    private LocalTime totalTimeInBedMinutes;
    private SleepFeeling feeling;
    private Instant createdAt;
    private Instant updatedAt;

    private Sleep(String id, LocalDate date, LocalTime timeInBedStart, LocalTime timeInBedEnd,
                  LocalTime totalTimeInBedMinutes, SleepFeeling feeling, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.date = date;
        this.timeInBedStart = timeInBedStart;
        this.timeInBedEnd = timeInBedEnd;
        this.totalTimeInBedMinutes = totalTimeInBedMinutes;
        this.feeling = feeling;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Sleep newSleep() {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        final var currentDate = LocalDate.now();
        final var startTimeInBed = LocalTime.now();

        return new Sleep(id, currentDate, startTimeInBed, null, null, null,
                now, null);
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeInBedStart() {
        return timeInBedStart;
    }

    public LocalTime getTimeInBedEnd() {
        return timeInBedEnd;
    }

    public LocalTime getTotalTimeInBedMinutes() {
        return totalTimeInBedMinutes;
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
}
