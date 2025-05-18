package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.enums.SleepFeeling;
import com.noom.interview.sleep.exceptions.WokeUpSleepException;
import com.noom.interview.sleep.repository.SleepRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class WokeUpSleepUseCase {
    private final SleepRepository sleepRepository;

    public WokeUpSleepUseCase(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    public String execute(String id, String morningFeeling) {
        try {
            final Sleep sleep = sleepRepository.getSleep(id);

            Arrays.stream(SleepFeeling.values())
                    .filter(sleepFeeling -> sleepFeeling.name().equals(morningFeeling))
                    .findFirst()
                    .ifPresentOrElse(
                            sleepFeeling -> sleep.setMorningFeeling(sleepFeeling.name()),
                            () -> {
                                throw new WokeUpSleepException("Invalid morning feeling: " + morningFeeling);
                            }
                    );

            sleep.updateSleep(sleep);

            return sleepRepository.updateSleep(sleep);
        }
        catch (Exception e){
            throw new WokeUpSleepException("Sleep not found with id: " + id);
        }


    }
}
