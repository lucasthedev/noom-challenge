package com.noom.interview.sleep.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SleepFetchMorningFeelingFrequencyRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private SleepFetchMorningFeelingFrequencyRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new SleepFetchMorningFeelingFrequencyRepository(jdbcTemplate);
    }

    @Test
    void shouldReturnMorningFeelingFrequencyMap() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("BAD", 3);
        expected.put("OK", 5);
        expected.put("GOOD", 8);

        when(jdbcTemplate.query(anyString(), (ResultSetExtractor<Map<String, Integer>>) any()))
                .thenReturn(expected);

        Map<String, Integer> result = repository.fetchMorningFeelingFrequency();

        assertEquals(expected, result);
        assertEquals(3, result.get("BAD"));
        assertEquals(5, result.get("OK"));
        assertEquals(8, result.get("GOOD"));
    }
}
