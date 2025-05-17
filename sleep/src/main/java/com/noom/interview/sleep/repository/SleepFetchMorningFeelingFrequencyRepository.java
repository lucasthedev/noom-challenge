package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.repository.queries.MorningFeelingFrequencyQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SleepFetchMorningFeelingFrequencyRepository {
    private final JdbcTemplate jdbcTemplate;

    public SleepFetchMorningFeelingFrequencyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Integer> fetchMorningFeelingFrequency() {
        String sql = MorningFeelingFrequencyQuery.MORNING_FEELING_FREQUENCY;
        return jdbcTemplate.query(sql, rs -> {
            Map<String, Integer> result = new HashMap<>();
            while (rs.next()) {
                result.put(rs.getString("morning_feel"), rs.getInt("freq"));
            }
            return result;
        });
    }
}
