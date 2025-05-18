package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.domain.Sleep;
import com.noom.interview.sleep.exceptions.SleepRecordNotFoundException;
import com.noom.interview.sleep.repository.SleepRepository;
import org.springframework.stereotype.Service;

@Service
public class GetSleepByIdUseCase {
    private final SleepRepository sleepRepository;

    public GetSleepByIdUseCase(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    public Sleep execute(String id) {
        try{
            return sleepRepository.getSleep(id);
        }
        catch (Exception e){
            throw new SleepRecordNotFoundException("Sleep not found with id: " + id);
        }
    }
}
