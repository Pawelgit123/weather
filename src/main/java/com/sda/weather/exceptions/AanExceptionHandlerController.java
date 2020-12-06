package com.sda.weather.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class AanExceptionHandlerController {

    @ExceptionHandler(ForecastAPiFailure.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    void forecastApiFailure(ForecastAPiFailure e){
        log.error(e.getMessage());
    }

    @ExceptionHandler(BlankSpaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void blankSpace(BlankSpaceException e) {
        log.error(e.getMessage());
    }

    @ExceptionHandler(DataOutOfBound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void dataOufOfBound(DataOutOfBound e) {
        log.error(e.getMessage());
    }

    @ExceptionHandler({BadLocalisationCreation.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badLocalisationCreation(RuntimeException e) {
        log.error(e.getMessage());
    }

    @ExceptionHandler({NotFoundException.class, })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFoundException(NotFoundException e) {
        log.error(e.getMessage());
    }
}
