package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.repository.queries.AverageStartBedTimeQuery;
import com.noom.interview.sleep.repository.queries.WakeTimeQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SleepBedWakeAveragesRepository {
    private final JdbcTemplate jdbcTemplate;

    public SleepBedWakeAveragesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LocalDateTime> fetchBedTimes() {
        return jdbcTemplate.query(AverageStartBedTimeQuery.AVERAGE_START_BED_TIME,
                (r, i) -> r.getTimestamp("start_bed_time").toLocalDateTime());
    }

    public List<LocalDateTime> fetchWakeTimes() {
        return jdbcTemplate.query(WakeTimeQuery.WAKE_TIME_QUERY,
                (r, i) -> r.getTimestamp("end_bed_time").toLocalDateTime());
    }
}
