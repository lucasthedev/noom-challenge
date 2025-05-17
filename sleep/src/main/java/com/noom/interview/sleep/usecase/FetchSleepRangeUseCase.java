package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.repository.SleepRetrieveRangeRepository;
import org.springframework.stereotype.Service;

@Service
public class FetchSleepRangeUseCase {
    private final SleepRetrieveRangeRepository sleepRetrieveRangeRepository;

    public FetchSleepRangeUseCase(SleepRetrieveRangeRepository sleepRetrieveRangeRepository) {
        this.sleepRetrieveRangeRepository = sleepRetrieveRangeRepository;
    }

    public SleepRetrieveRangeRepository.DateInterval execute() {
        return sleepRetrieveRangeRepository.getSleepDateInterval()
                .orElseThrow(() -> new RuntimeException("No sleep data found in the last 30 days"));
    }
}
