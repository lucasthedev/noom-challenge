package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.repository.SleepRepositoryJdbc;
import org.springframework.stereotype.Service;

@Service
public class CreateSleepUseCase {

    private final SleepRepositoryJdbc sleepRepository;

    public CreateSleepUseCase(SleepRepositoryJdbc sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    public String execute() {
        final Sleep sleep = Sleep.newSleep();
        return sleepRepository.saveSleep(sleep);
    }

}
