package com.noom.interview.sleep.repository.queries;

public class RangeAndAverageTimeInBedQuery {
    public static final String AVERAGE_QUERY =
            "SELECT " +
            "  MIN(sleep_date) AS min_date, " +
            "  MAX(sleep_date) AS max_date, " +
            "  AVG(total_bed_time_minutes) / 60.0 AS avg_time_in_bed_hours " +
            "FROM sleep " +
            "WHERE sleep_date >= CURRENT_DATE - INTERVAL '30 days'";
}
