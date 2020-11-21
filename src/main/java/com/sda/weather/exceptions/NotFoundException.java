package com.sda.weather.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super("NOT FOUND " + message);
    }
}
