package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.exceptions.SleepRangeNotFoundException;
import com.noom.interview.sleep.repository.SleepFetchingAveragesRepository;
import org.springframework.stereotype.Service;

@Service
public class GetSleepAveragesUseCase {
    private final SleepFetchingAveragesRepository sleepFetchingAveragesRepository;

    public GetSleepAveragesUseCase(SleepFetchingAveragesRepository sleepFetchingAveragesRepository) {
        this.sleepFetchingAveragesRepository = sleepFetchingAveragesRepository;
    }

    public SleepFetchingAveragesRepository.Data execute() {
        var sleepAverages = sleepFetchingAveragesRepository.getSleepAverages().get();

        if (sleepAverages.getStartDate() == null || sleepAverages.getEndDate() == null) {
            throw new SleepRangeNotFoundException("No sleep data found in the last 30 days");
        }

        return sleepAverages;
    }
}
