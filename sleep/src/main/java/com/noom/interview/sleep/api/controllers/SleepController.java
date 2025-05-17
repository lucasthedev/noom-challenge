package com.noom.interview.sleep.api.controllers;

import com.noom.interview.sleep.api.request.WokeUpSleepRequest;
import com.noom.interview.sleep.api.response.SleepRangeResponse;
import com.noom.interview.sleep.repository.SleepIntervalRepository;
import com.noom.interview.sleep.usecase.CreateSleepUseCase;
import com.noom.interview.sleep.usecase.FetchSleepRangeUseCase;
import com.noom.interview.sleep.usecase.WokeUpSleepUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SleepController {

    private final CreateSleepUseCase createSleepUseCase;
    private final WokeUpSleepUseCase wokeUpSleepUseCase;
    private final FetchSleepRangeUseCase fetchSleepRangeUseCase;

    public SleepController(CreateSleepUseCase createSleepUseCase,
                           WokeUpSleepUseCase wokeUpSleepUseCase,
                           FetchSleepRangeUseCase fetchSleepRangeUseCase) {
        this.createSleepUseCase = createSleepUseCase;
        this.wokeUpSleepUseCase = wokeUpSleepUseCase;
        this.fetchSleepRangeUseCase = fetchSleepRangeUseCase;
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

    @GetMapping("/sleep-range")
    public ResponseEntity<SleepRangeResponse> getSleepRange() {
        SleepIntervalRepository.DateInterval dateInterval = fetchSleepRangeUseCase.execute();
        SleepRangeResponse sleepRangeResponse = new SleepRangeResponse(dateInterval.getStart(), dateInterval.getEnd());
        return ResponseEntity.ok(sleepRangeResponse);
    }

}
