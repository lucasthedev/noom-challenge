package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.api.response.SleepRangeResponse;
import com.noom.interview.sleep.repository.queries.AverageQuery;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public class SleepFetchingAveragesRepository {

    private final JdbcTemplate jdbcTemplate;

    public SleepFetchingAveragesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Data> getSleepAverages() {

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
        private LocalDate startDate;
        private LocalDate endDate;
        private double avgTimeInBedHours;
        private LocalTime avgBedTime;
        private LocalTime avgWakeUpTime;

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

        public SleepRangeResponse toResponse(Data data) {
            return new SleepRangeResponse(
                    data.getStartDate(),
                    data.getEndDate(),
                    data.getAvgTimeInBedHours(),
                    data.getAvgBedTime(),
                    data.getAvgWakeUpTime()
            );
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

        public LocalTime getAvgBedTime() {
            return avgBedTime;
        }

        public LocalTime getAvgWakeUpTime() {
            return avgWakeUpTime;
        }
    }
}
