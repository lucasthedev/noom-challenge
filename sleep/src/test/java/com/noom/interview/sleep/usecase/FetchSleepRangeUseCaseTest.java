package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.repository.SleepIntervalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FetchSleepRangeUseCaseTest {
    private SleepIntervalRepository repository;
    private FetchSleepRangeUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(SleepIntervalRepository.class);
        useCase = new FetchSleepRangeUseCase(repository);
    }

    @Test
    void shouldReturnIntervalWhenExists() {
        var interval = new SleepIntervalRepository.Data(
                LocalDate.now().minusDays(10),
                LocalDate.now(),
                100L,
                LocalTime.now(),
                LocalTime.now()
        );
        when(repository.getSleepDateInterval()).thenReturn(Optional.of(interval));

        var result = useCase.execute();

        assertNotNull(result);
        assertEquals(interval.getStartDate(), result.getStartDate());
        assertEquals(interval.getEndDate(), result.getEndDate());
    }

    @Test
    void shouldThrowExceptionWhenDotNotExists() {
        when(repository.getSleepDateInterval()).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute());
        assertEquals("No sleep data found in the last 30 days", ex.getMessage());
    }
}
