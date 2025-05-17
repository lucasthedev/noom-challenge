package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.domain.Sleep;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
@Transactional
public class SleepRepositoryJdbc {

    private final JdbcTemplate jdbcTemplate;

    public SleepRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String saveSleep(Sleep sleep) {

        String sql = "INSERT INTO sleep (id, sleep_date, start_bed_time, end_bed_time, total_bed_time, morning_feel, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?::morning_feel, ?, ?)";

        jdbcTemplate.update(sql,
                sleep.getId(),
                sleep.getDate(),
                sleep.getTimeInBedStart(),
                sleep.getTimeInBedEnd(),
                sleep.getTotalTimeInBedMinutes(),
                sleep.getFeeling(),
                Timestamp.from(sleep.getCreatedAt()),
                null);

        return sleep.getId();
    }

}
