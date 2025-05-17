package com.noom.interview.sleep.repository.queries;

public class AverageQuery {
    public static final String AVERAGE_QUERY =
            "SELECT " +
            "  MIN(sleep_date) AS min_date, " +
            "  MAX(sleep_date) AS max_date, " +
            "  AVG(total_bed_time_minutes) / 60.0 AS avg_time_in_bed_hours, " +
            "  AVG(EXTRACT(HOUR FROM start_bed_time) + EXTRACT(MINUTE FROM start_bed_time)/60.0) AS avg_start_bed_time_hour, " +
            "  AVG(EXTRACT(HOUR FROM end_bed_time) + EXTRACT(MINUTE FROM end_bed_time)/60.0) AS avg_wake_time_hour " +
            "FROM sleep " +
            "WHERE sleep_date >= CURRENT_DATE - INTERVAL '30 days'";
}
