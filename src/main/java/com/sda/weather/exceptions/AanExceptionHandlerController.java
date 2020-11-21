package com.sda.weather.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class AanExceptionHandlerController {

    @ExceptionHandler(BadLocalisationCreation.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badLocalisationCreation(BadLocalisationCreation e){
        log.error(e.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFoundException(NotFoundException e){
        log.error(e.getMessage());
    }
    @ExceptionHandler(DataOutOfBound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void dataOutOfBound(DataOutOfBound e){
        log.error(e.getMessage());
    }
}
