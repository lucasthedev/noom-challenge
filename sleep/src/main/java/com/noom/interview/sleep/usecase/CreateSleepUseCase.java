package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.repository.SleepRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateSleepUseCase {

    private final SleepRepository sleepRepository;

    public CreateSleepUseCase(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    public String execute() {
        final Sleep sleep = new Sleep();
        return sleepRepository.saveSleep(sleep);
    }

}
