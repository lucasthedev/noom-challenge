package com.noom.interview.sleep.api.controllers;

import com.noom.interview.sleep.usecase.CreateSleepUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleepController {

    private final CreateSleepUseCase createSleepUseCase;

    public SleepController(CreateSleepUseCase createSleepUseCase) {
        this.createSleepUseCase = createSleepUseCase;
    }

    @PostMapping("/register-sleep")
    public ResponseEntity<String> saveSleep(){
        String id = createSleepUseCase.execute();
        return ResponseEntity.ok(id);
    }

}
