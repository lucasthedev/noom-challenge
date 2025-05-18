package com.noom.interview.sleep.api.controllers;

import com.noom.interview.sleep.api.request.WokeUpSleepRequest;
import com.noom.interview.sleep.api.response.SleepAveragesResponse;
import com.noom.interview.sleep.repository.SleepFetchingAveragesRepository;
import com.noom.interview.sleep.usecase.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
public class SleepController {

    private final CreateSleepUseCase createSleepUseCase;
    private final WokeUpSleepUseCase wokeUpSleepUseCase;
    private final GetSleepAveragesUseCase getSleepAveragesUseCase;
    private final GetSleepMorningFeelingFrequencyUseCase getSleepMorningFeelingFrequencyUseCase;
    private final GetBedWakeAveragesUseCase getBedWakeAveragesUseCase;
    private final GetSleepByIdUseCase getSleepByIdUseCase;

    public SleepController(CreateSleepUseCase createSleepUseCase,
                           WokeUpSleepUseCase wokeUpSleepUseCase,
                           GetSleepAveragesUseCase getSleepAveragesUseCase,
                           GetSleepMorningFeelingFrequencyUseCase getSleepMorningFeelingFrequencyUseCase,
                           GetBedWakeAveragesUseCase getBedWakeAveragesUseCase,
                           GetSleepByIdUseCase getSleepByIdUseCase) {
        this.createSleepUseCase = createSleepUseCase;
        this.wokeUpSleepUseCase = wokeUpSleepUseCase;
        this.getSleepAveragesUseCase = getSleepAveragesUseCase;
        this.getSleepMorningFeelingFrequencyUseCase = getSleepMorningFeelingFrequencyUseCase;
        this.getBedWakeAveragesUseCase = getBedWakeAveragesUseCase;
        this.getSleepByIdUseCase = getSleepByIdUseCase;
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
        SleepFetchingAveragesRepository.Data dataAverages = getSleepAveragesUseCase.execute();
        GetBedWakeAveragesUseCase.BedWakeAverages bedWakeAverages = getBedWakeAveragesUseCase.execute().get();

        var response = new SleepAveragesResponse(
                dataAverages.getStartDate(),
                dataAverages.getEndDate(),
                dataAverages.getAvgTimeInBedHours(),
                bedWakeAverages.getAvgBedTime(),
                bedWakeAverages.getAvgWakeTime()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/sleep-frequency",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSleepFrequencyMorningFeeling() {

        Map<String, Integer> morningFeelingFrequency = getSleepMorningFeelingFrequencyUseCase.execute();

        return ResponseEntity.status(HttpStatus.OK).body(morningFeelingFrequency);
    }

    @GetMapping(value = "/sleep/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSleepById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getSleepByIdUseCase.execute(id));
    }

}
