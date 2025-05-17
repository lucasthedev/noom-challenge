package com.noom.interview.sleep.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class SleepRetrieveRangeRepository {

    private final JdbcTemplate jdbcTemplate;

    public SleepRetrieveRangeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<DateInterval> getSleepDateInterval() {
        String sql = "SELECT MIN(sleep_date) AS min_date, MAX(sleep_date) AS max_date FROM sleep WHERE sleep_date >= CURRENT_DATE - INTERVAL '30 days'";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                LocalDate min = rs.getDate("min_date") != null ? rs.getDate("min_date").toLocalDate() : null;
                LocalDate max = rs.getDate("max_date") != null ? rs.getDate("max_date").toLocalDate() : null;
                if (min != null && max != null) {
                    return Optional.of(new DateInterval(min, max));
                }
            }
            return Optional.empty();
        });
    }

    public static class DateInterval {
        private final LocalDate start;
        private final LocalDate end;

        public DateInterval(LocalDate start, LocalDate end) {
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
}
