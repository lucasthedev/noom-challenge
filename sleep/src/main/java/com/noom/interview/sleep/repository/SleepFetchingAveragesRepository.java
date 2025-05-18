package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.repository.queries.RangeAndAverageTimeInBedQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class SleepFetchingAveragesRepository {

    private final JdbcTemplate jdbcTemplate;

    public SleepFetchingAveragesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Data> getSleepAverages() {

        String sql = RangeAndAverageTimeInBedQuery.AVERAGE_QUERY;

        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                LocalDate min = rs.getDate("min_date") != null ? rs.getDate("min_date").toLocalDate() : null;
                LocalDate max = rs.getDate("max_date") != null ? rs.getDate("max_date").toLocalDate() : null;
                double avgTimeInBed = rs.getDouble("avg_time_in_bed_hours");
                return Optional.of(new Data(min, max, avgTimeInBed));
            }
            return Optional.empty();
        });

    }

    public static class Data {
        private LocalDate startDate;
        private LocalDate endDate;
        private double avgTimeInBedHours;

        public Data(LocalDate startDate, LocalDate endDate,
                    double avgTimeInBedHours) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.avgTimeInBedHours = avgTimeInBedHours;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public double getAvgTimeInBedHours() {
            return avgTimeInBedHours;
        }

        public LocalDate getEndDate() {
            return endDate;
        }
    }
}
