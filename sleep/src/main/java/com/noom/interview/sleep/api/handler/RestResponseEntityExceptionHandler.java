package com.noom.interview.sleep.api.handler;

import com.noom.interview.sleep.exceptions.SleepRangeNotFoundException;
import com.noom.interview.sleep.exceptions.WokeUpSleepException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler({WokeUpSleepException.class})
    public ResponseEntity<Object> handleDomainException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SleepRangeNotFoundException.class})
    public ResponseEntity<Object> handleSleepRangeNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
