package com.noom.interview.sleep.repository.queries;

public class WakeTimeQuery {
    public static final String WAKE_TIME_QUERY =
            "SELECT end_bed_time FROM sleep WHERE sleep_date >= CURRENT_DATE - INTERVAL '30 days'";
}
