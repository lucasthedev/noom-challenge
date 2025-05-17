package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.exceptions.SleepRangeNotFoundException;
import com.noom.interview.sleep.repository.SleepIntervalRepository;
import org.springframework.stereotype.Service;

@Service
public class FetchSleepRangeUseCase {
    private final SleepIntervalRepository sleepIntervalRepository;

    public FetchSleepRangeUseCase(SleepIntervalRepository sleepIntervalRepository) {
        this.sleepIntervalRepository = sleepIntervalRepository;
    }

    public SleepIntervalRepository.DateInterval execute() {
        return sleepIntervalRepository.getSleepDateInterval()
                .orElseThrow(() -> new SleepRangeNotFoundException("No sleep data found in the last 30 days"));
    }
}
