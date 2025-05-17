package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.enums.SleepFeeling;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
@Transactional
public class SleepRepository {

    private final JdbcTemplate jdbcTemplate;

    public SleepRepository(JdbcTemplate jdbcTemplate) {
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
                sleep.getTotalTimeInBed(),
                sleep.getFeeling(),
                Timestamp.from(sleep.getCreatedAt()),
                null);

        return sleep.getId();
    }

    public String updateSleep(Sleep sleep) {

        String sql = "UPDATE sleep SET end_bed_time = ?, total_bed_time = ?, morning_feel = ?::morning_feel, updated_at = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql,
                sleep.getTimeInBedEnd(),
                sleep.getTotalTimeInBed(),
                sleep.getFeeling() != null ? sleep.getFeeling().name() : null,
                Timestamp.from(sleep.getUpdatedAt()),
                sleep.getId());

        return sleep.getId();
    }

    public Sleep getSleep(String id) {
        String sql = "SELECT * FROM sleep WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                        new Sleep(
                                rs.getString("id"),
                                rs.getDate("sleep_date").toLocalDate(),
                                rs.getTime("start_bed_time").toLocalTime(),
                                rs.getTime("end_bed_time") != null ? rs.getTime("end_bed_time").toLocalTime() : null,
                                rs.getTime("total_bed_time") != null ? rs.getTime("total_bed_time").toLocalTime() : null,
                                rs.getString("morning_feel") != null ? SleepFeeling.valueOf(rs.getString("morning_feel")) : null,
                                rs.getTimestamp("created_at").toInstant(),
                                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toInstant() : null
                        ),
                id
        );
    }

}
