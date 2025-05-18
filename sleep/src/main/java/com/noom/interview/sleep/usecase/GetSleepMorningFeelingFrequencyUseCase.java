package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.repository.SleepFetchMorningFeelingFrequencyRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GetSleepMorningFeelingFrequencyUseCase {
    private final SleepFetchMorningFeelingFrequencyRepository sleepFetchMorningFeelingFrequencyRepository;

    public GetSleepMorningFeelingFrequencyUseCase(SleepFetchMorningFeelingFrequencyRepository sleepFetchMorningFeelingFrequencyRepository) {
        this.sleepFetchMorningFeelingFrequencyRepository = sleepFetchMorningFeelingFrequencyRepository;
    }

    public Map<String, Integer> execute() {
        return sleepFetchMorningFeelingFrequencyRepository.fetchMorningFeelingFrequency();
    }
}
