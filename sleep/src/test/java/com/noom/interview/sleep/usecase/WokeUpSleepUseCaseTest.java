package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.enums.SleepFeeling;
import com.noom.interview.sleep.exceptions.WokeUpSleepException;
import com.noom.interview.sleep.repository.SleepRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WokeUpSleepUseCaseTest {
    private SleepRepository sleepRepository;
    private WokeUpSleepUseCase wokeUpSleepUseCase;

    @BeforeEach
    void setUp() {
        sleepRepository = Mockito.mock(SleepRepository.class);
        wokeUpSleepUseCase = new WokeUpSleepUseCase(sleepRepository);
    }

    @Test
    void shouldUpdateMorningFeelingWithSuccess() {
        Sleep sleep = new Sleep();
        Mockito.when(sleepRepository.getSleep("123")).thenReturn(sleep);
        Mockito.when(sleepRepository.updateSleep(Mockito.any())).thenReturn("123");

        String result = wokeUpSleepUseCase.execute("123", SleepFeeling.GOOD.name());

        Assertions.assertEquals("123", result);
        Mockito.verify(sleepRepository, Mockito.times(1)).updateSleep(sleep);
    }

    @Test
    void shouldThrowExceptionWhenSleepNotFound() {
        Mockito.when(sleepRepository.getSleep("notfound")).thenReturn(null);

        Assertions.assertThrows(WokeUpSleepException.class, () ->
                wokeUpSleepUseCase.execute("notfound", SleepFeeling.GOOD.name())
        );
    }

    @Test
    void shouldThrowExceptionWhenMorningFeelingIsInvalid() {
        Sleep sleep = new Sleep();
        Mockito.when(sleepRepository.getSleep("123")).thenReturn(sleep);

        Assertions.assertThrows(WokeUpSleepException.class, () ->
                wokeUpSleepUseCase.execute("123", "INVALID_FEELING")
        );
    }
}
