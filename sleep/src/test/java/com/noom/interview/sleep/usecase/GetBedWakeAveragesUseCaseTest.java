package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.repository.SleepBedWakeAveragesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetBedWakeAveragesUseCaseTest {

    private SleepBedWakeAveragesRepository repository;
    private GetBedWakeAveragesUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(SleepBedWakeAveragesRepository.class);
        useCase = new GetBedWakeAveragesUseCase(repository);
    }

    @Test
    void shouldReturnEmptyWhenNoData() {
        when(repository.fetchBedTimes()).thenReturn(Collections.emptyList());
        when(repository.fetchWakeTimes()).thenReturn(Collections.emptyList());

        Optional<GetBedWakeAveragesUseCase.BedWakeAverages> result = useCase.execute();

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAveragesWhenDataExists() {
        when(repository.fetchBedTimes()).thenReturn(Arrays.asList(
                LocalDateTime.of(2024, 7, 1, 23, 0),
                LocalDateTime.of(2024, 7, 2, 0, 0)
        ));
        when(repository.fetchWakeTimes()).thenReturn(Arrays.asList(
                LocalDateTime.of(2024, 7, 2, 6, 30),
                LocalDateTime.of(2024, 7, 3, 7, 0)
        ));

        Optional<GetBedWakeAveragesUseCase.BedWakeAverages> result = useCase.execute();

        assertTrue(result.isPresent());
        LocalTime avgBedTime = result.get().getAvgBedTime();
        LocalTime avgWakeTime = result.get().getAvgWakeTime();

        assertEquals(LocalTime.of(23, 30), avgBedTime);
        assertEquals(LocalTime.of(6, 45), avgWakeTime);
    }
}