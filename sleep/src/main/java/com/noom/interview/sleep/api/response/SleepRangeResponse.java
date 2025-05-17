package com.noom.interview.sleep.api.response;

import java.time.LocalDate;

public class SleepRangeResponse {
    private  LocalDate start;
    private LocalDate end;

    public SleepRangeResponse(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
