package com.noom.interview.sleep.repository.queries;

public class AverageStartBedTimeQuery {
    public static final String AVERAGE_START_BED_TIME =
            "SELECT start_bed_time "+
                    "FROM sleep "+
                    "WHERE sleep_date >= CURRENT_DATE - INTERVAL '30 days'";

}
