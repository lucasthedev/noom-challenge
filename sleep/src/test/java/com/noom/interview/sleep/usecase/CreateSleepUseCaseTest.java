package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.repository.SleepRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateSleepUseCaseTest {
    private SleepRepository sleepRepository;
    private CreateSleepUseCase createSleepUseCase;

    @BeforeEach
    void setUp() {
        sleepRepository = Mockito.mock(SleepRepository.class);
        createSleepUseCase = new CreateSleepUseCase(sleepRepository);
    }

    @Test
    void shouldCreateSleepAndReturnId() {
        Mockito.when(sleepRepository.saveSleep(Mockito.any(Sleep.class))).thenReturn("sleep-id-1");

        String result = createSleepUseCase.execute();

        Assertions.assertEquals("sleep-id-1", result);
    }
}
