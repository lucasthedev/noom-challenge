package com.noom.interview.sleep.repository;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.enums.SleepFeeling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SleepRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private SleepRepository sleepRepository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        sleepRepository = new SleepRepository(jdbcTemplate);
    }

    @Test
    void saveNewSleep() {
        Sleep sleep = new Sleep();
        final var expectedId = sleep.getId();
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(1);

        String id = sleepRepository.saveSleep(sleep);

        assertNotNull(id);
        assertEquals(expectedId, id);
        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    void updateSleep() {
        Sleep sleep = new Sleep("1", LocalDate.now(), LocalTime.of(22, 0), LocalTime.of(6, 0),
                LocalTime.of(8, 0), SleepFeeling.GOOD, Instant.now(), Instant.now());

        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any())).thenReturn(1);

        String id = sleepRepository.updateSleep(sleep);

        assertEquals("1", id);
        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any(), any(), any());
    }

    @Test
    void getSleepById() {
        Sleep expectedSleep = new Sleep("1", LocalDate.now(), LocalTime.of(22, 0), LocalTime.of(6, 0),
                LocalTime.of(8, 0), SleepFeeling.GOOD, Instant.now(), Instant.now());

        when(jdbcTemplate.queryForObject(anyString(), any(org.springframework.jdbc.core.RowMapper.class), any()))
                .thenReturn(expectedSleep);

        Sleep sleep = sleepRepository.getSleep("1");

        assertNotNull(sleep);
        assertEquals("1", sleep.getId());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(org.springframework.jdbc.core.RowMapper.class), eq("1"));
    }


}
