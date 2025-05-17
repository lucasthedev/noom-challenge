package com.noom.interview.sleep.api.controllers;

import com.noom.interview.sleep.api.request.WokeUpSleepRequest;
import com.noom.interview.sleep.api.response.SleepRangeResponse;
import com.noom.interview.sleep.repository.SleepFetchingAveragesRepository;
import com.noom.interview.sleep.usecase.CreateSleepUseCase;
import com.noom.interview.sleep.usecase.FetchSleepAveragesUseCase;
import com.noom.interview.sleep.usecase.FetchSleepMorningFeelingFrequencyUseCase;
import com.noom.interview.sleep.usecase.WokeUpSleepUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SleepController {

    private final CreateSleepUseCase createSleepUseCase;
    private final WokeUpSleepUseCase wokeUpSleepUseCase;
    private final FetchSleepAveragesUseCase fetchSleepAveragesUseCase;
    private final FetchSleepMorningFeelingFrequencyUseCase fetchSleepMorningFeelingFrequencyUseCase;

    public SleepController(CreateSleepUseCase createSleepUseCase,
                           WokeUpSleepUseCase wokeUpSleepUseCase,
                           FetchSleepAveragesUseCase fetchSleepAveragesUseCase,
                           FetchSleepMorningFeelingFrequencyUseCase fetchSleepMorningFeelingFrequencyUseCase) {
        this.createSleepUseCase = createSleepUseCase;
        this.wokeUpSleepUseCase = wokeUpSleepUseCase;
        this.fetchSleepAveragesUseCase = fetchSleepAveragesUseCase;
        this.fetchSleepMorningFeelingFrequencyUseCase = fetchSleepMorningFeelingFrequencyUseCase;
    }

    @PostMapping(value = "/register-sleep",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveSleep(){
        String id = createSleepUseCase.execute();
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping(value = "/woke-up-sleep/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> wokeUpSleep(@PathVariable String id, @RequestBody WokeUpSleepRequest request){
        String sleepId = wokeUpSleepUseCase.execute(id, request.getMorningFeeling());
        return ResponseEntity.status(HttpStatus.OK).body(sleepId);
    }

    @GetMapping(value = "/sleep-averages",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSleepAverages() {
        SleepFetchingAveragesRepository.Data dateInterval = fetchSleepAveragesUseCase.execute();

        SleepRangeResponse sleepRangeResponse = new SleepRangeResponse(dateInterval.getStartDate(),
                dateInterval.getEndDate(),
                dateInterval.getAvgTimeInBedHours(),
                dateInterval.getAvgBedTime(),
                dateInterval.getAvgWakeUpTime());

        return ResponseEntity.status(HttpStatus.OK).body(sleepRangeResponse);
    }

    @GetMapping(value = "/sleep-frequency",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSleepFrequencyMorningFeeling() {

        Map<String, Integer> morningFeelingFrequency = fetchSleepMorningFeelingFrequencyUseCase.execute();

        return ResponseEntity.status(HttpStatus.OK).body(morningFeelingFrequency);
    }

}
