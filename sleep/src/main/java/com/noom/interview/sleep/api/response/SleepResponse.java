package com.noom.interview.sleep.api.response;

import com.noom.interview.sleep.domain.Sleep;

public class SleepResponse {
    private final String id;
    private final String date;
    private final String timeInBedStart;
    private final String timeInBedEnd;
    private final String totalTimeInBed;
    private final String feeling;

    public SleepResponse(Sleep sleep) {
        this.id = sleep.getId();
        this.date = sleep.getDate() != null ? sleep.getDate().toString() : null;
        this.timeInBedStart = sleep.getTimeInBedStart() != null ? sleep.getTimeInBedStart().toString() : null;
        this.timeInBedEnd = sleep.getTimeInBedEnd() != null ? sleep.getTimeInBedEnd().toString() : null;
        if (sleep.getTotalTimeInBed() != null) {
            long minutes = sleep.getTotalTimeInBed().toMinutes();
            long hours = minutes / 60;
            long mins = minutes % 60;
            this.totalTimeInBed = String.format("%02d:%02d", hours, mins);
        } else {
            this.totalTimeInBed = null;
        }
        this.feeling = sleep.getFeeling() != null ? sleep.getFeeling().name() : null;
    }

    public String getId() {
        return id;
    }

    public String getFeeling() {
        return feeling;
    }

    public String getTotalTimeInBed() {
        return totalTimeInBed;
    }

    public String getTimeInBedEnd() {
        return timeInBedEnd;
    }

    public String getTimeInBedStart() {
        return timeInBedStart;
    }

    public String getDate() {
        return date;
    }
}