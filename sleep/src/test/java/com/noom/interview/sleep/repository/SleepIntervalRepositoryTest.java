package com.noom.interview.sleep.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SleepIntervalRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private SleepIntervalRepository repository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        repository = new SleepIntervalRepository(jdbcTemplate);
    }

    @Test
    void shouldReturnDateIntervalWhenDataExists() throws SQLException, SQLException {
        LocalDate minDate = LocalDate.now().minusDays(10);
        LocalDate maxDate = LocalDate.now();

        when(jdbcTemplate.query(anyString(), (ResultSetExtractor<Object>) any()))
                .thenReturn(Optional.of(new SleepIntervalRepository.Data(minDate, maxDate,
                        100L,
                        LocalTime.now(),
                        LocalTime.now())));

        Optional<SleepIntervalRepository.Data> result = repository.getSleepDateInterval();

        assertTrue(result.isPresent());
        assertEquals(minDate, result.get().getStartDate());
        assertEquals(maxDate, result.get().getEndDate());
    }

}
