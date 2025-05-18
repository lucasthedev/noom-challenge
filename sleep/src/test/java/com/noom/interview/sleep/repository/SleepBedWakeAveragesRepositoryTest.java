package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.repository.queries.AverageStartBedTimeQuery;
import com.noom.interview.sleep.repository.queries.WakeTimeQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SleepBedWakeAveragesRepositoryTest {
    private JdbcTemplate jdbcTemplate;
    private SleepBedWakeAveragesRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new SleepBedWakeAveragesRepository(jdbcTemplate);
    }

    @Test
    void shouldReturnListOfBedTimes() {
        List<LocalDateTime> expected = Arrays.asList(
                LocalDateTime.of(2024, 7, 1, 23, 0),
                LocalDateTime.of(2024, 7, 2, 22, 45)
        );
        when(jdbcTemplate.query(eq(AverageStartBedTimeQuery.AVERAGE_START_BED_TIME), any(RowMapper.class)))
                .thenReturn(expected);

        List<LocalDateTime> result = repository.fetchBedTimes();

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnListOfWakeTimes() {
        List<LocalDateTime> expected = Arrays.asList(
                LocalDateTime.of(2024, 7, 2, 6, 30),
                LocalDateTime.of(2024, 7, 3, 7, 0)
        );
        when(jdbcTemplate.query(eq(WakeTimeQuery.WAKE_TIME_QUERY), any(RowMapper.class)))
                .thenReturn(expected);

        List<LocalDateTime> result = repository.fetchWakeTimes();

        assertEquals(expected, result);
    }
}
