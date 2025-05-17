package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.repository.queries.AverageQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public class SleepIntervalRepository {

    private final JdbcTemplate jdbcTemplate;

    public SleepIntervalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Data> getSleepDateInterval() {

        String sql = AverageQuery.AVERAGE_QUERY;

        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                LocalDate min = rs.getDate("min_date") != null ? rs.getDate("min_date").toLocalDate() : null;
                LocalDate max = rs.getDate("max_date") != null ? rs.getDate("max_date").toLocalDate() : null;

                double avgTimeInBed = rs.getDouble("avg_time_in_bed_hours");
                double avgBedTimeHour = rs.getDouble("avg_start_bed_time_hour");
                double avgWakeTimeHour = rs.getDouble("avg_wake_time_hour");

                LocalTime avgBedTime = LocalTime.of(
                        (int) avgBedTimeHour,
                        (int) Math.round((avgBedTimeHour - Math.floor(avgBedTimeHour)) * 60)
                );
                LocalTime avgWakeTime = LocalTime.of(
                        (int) avgWakeTimeHour,
                        (int) Math.round((avgWakeTimeHour - Math.floor(avgWakeTimeHour)) * 60)
                );

                if (min != null && max != null) {
                    return Optional.of(new Data(
                            min, max, avgTimeInBed, avgBedTime, avgWakeTime
                    ));
                }
            }
            return Optional.empty();
        });
    }

    public static class Data {
        private  LocalDate startDate;
        private LocalDate endDate;
        private final double avgTimeInBedHours;
        private final LocalTime avgBedTime;
        private final LocalTime avgWakeUpTime;

        public Data(LocalDate startDate, LocalDate endDate,
                    double avgTimeInBedHours,
                    LocalTime avgBedTime,
                    LocalTime avgWakeUpTime) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.avgTimeInBedHours = avgTimeInBedHours;
            this.avgBedTime = avgBedTime;
            this.avgWakeUpTime = avgWakeUpTime;
        }

        public LocalDate getStartDate() { return startDate; }
        public LocalDate getEndDate() { return endDate; }
        public double getAvgTimeInBedHours() { return avgTimeInBedHours; }
        public LocalTime getAvgBedTime() { return avgBedTime; }
        public LocalTime getAvgWakeUpTime() { return avgWakeUpTime; }
    }
}
