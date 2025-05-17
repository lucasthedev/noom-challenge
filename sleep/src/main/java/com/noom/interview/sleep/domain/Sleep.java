package com.noom.interview.sleep.domain;

import com.noom.interview.sleep.enums.SleepFeeling;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


public class Sleep {
    private String id;
    private LocalDate date;
    private LocalTime timeInBedStart;
    private LocalTime timeInBedEnd;
    private LocalTime totalTimeInBed;
    private SleepFeeling feeling;
    private Instant createdAt;
    private Instant updatedAt;

    private final static int BAD_MINUTES = 300;
    private final static int OK_MINUTES = 360;

    public Sleep(String id, LocalDate date, LocalTime timeInBedStart, LocalTime timeInBedEnd,
                 LocalTime totalTimeInBed, SleepFeeling feeling, Instant createdAt, Instant updatedAt) {
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
        final var startTimeInBed = LocalTime.now();

        this.id = id;
        this.date = currentDate;
        this.timeInBedStart = startTimeInBed;
        this.timeInBedEnd = null;
        this.totalTimeInBed = null;
        this.feeling = null;
        this.createdAt = now;
        this.updatedAt = null;
    }

    public void updateSleep(Sleep sleep) {
        this.timeInBedEnd = LocalTime.now();
        this.totalTimeInBed = sleep.getTimeInBedEnd().minusHours(timeInBedStart.getHour())
                .minusMinutes(timeInBedStart.getMinute());
        this.feeling = setFeeling(sleep);
        this.updatedAt = Instant.now();
    }

    public static SleepFeeling setFeeling(Sleep sleep) {
        if (sleep.getTotalTimeInBed() == null) {
            return null;
        }
        int totalMinutes = sleep.getTotalTimeInBed().getHour() * 60 + sleep.getTotalTimeInBed().getMinute();

        if (totalMinutes <= BAD_MINUTES) { // 5 horas ou menos
            return SleepFeeling.BAD;
        } else if (totalMinutes > BAD_MINUTES && totalMinutes <= OK_MINUTES) { // entre 5 e 6 horas
            return SleepFeeling.OK;
        } else { // mais de 6 horas
            return SleepFeeling.GOOD;
        }
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

    public LocalTime getTotalTimeInBed() {
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
}
