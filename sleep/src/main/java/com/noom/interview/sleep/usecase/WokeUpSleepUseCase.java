package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.repository.SleepRepository;
import org.springframework.stereotype.Service;

@Service
public class WokeUpSleepUseCase {
    private final SleepRepository sleepRepository;

    public WokeUpSleepUseCase(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    public String execute(String id) {
        final Sleep sleep = sleepRepository.getSleep(id);

        if (sleep == null) {
            throw new IllegalArgumentException("Sleep not found with id: " + id);
        }

        sleep.updateSleep(sleep);

        return sleepRepository.updateSleep(sleep);
    }
}
