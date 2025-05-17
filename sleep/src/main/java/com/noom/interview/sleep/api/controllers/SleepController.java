package com.noom.interview.sleep.api.controllers;

import com.noom.interview.sleep.api.request.WokeUpSleepRequest;
import com.noom.interview.sleep.usecase.CreateSleepUseCase;
import com.noom.interview.sleep.usecase.WokeUpSleepUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> wokeUpSleep(@PathVariable String id, @RequestBody WokeUpSleepRequest request){
        String sleepId = wokeUpSleepUseCase.execute(id, request.getMorningFeeling());
        return ResponseEntity.ok(sleepId);
    }

}
