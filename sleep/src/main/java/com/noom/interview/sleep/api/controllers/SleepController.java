package com.noom.interview.sleep.api.controllers;

import com.noom.interview.sleep.usecase.CreateSleepUseCase;
import com.noom.interview.sleep.usecase.WokeUpSleepUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleepController {

    private final CreateSleepUseCase createSleepUseCase;
    private final WokeUpSleepUseCase wokeUpSleepUseCase;

    public SleepController(CreateSleepUseCase createSleepUseCase, WokeUpSleepUseCase wokeUpSleepUseCase) {
        this.createSleepUseCase = createSleepUseCase;
        this.wokeUpSleepUseCase = wokeUpSleepUseCase;
    }

    @PostMapping("/register-sleep")
    public ResponseEntity<String> saveSleep(){
        String id = createSleepUseCase.execute();
        return ResponseEntity.ok(id);
    }

    @PatchMapping("/woke-up-sleep/{id}")
    public ResponseEntity<String> wokeUpSleep(@PathVariable String id){
        String sleepId = wokeUpSleepUseCase.execute(id);
        return ResponseEntity.ok(sleepId);
    }

}
