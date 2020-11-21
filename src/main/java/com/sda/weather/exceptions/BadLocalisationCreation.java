package com.sda.weather.exceptions;

public class BadLocalisationCreation extends RuntimeException {

    public BadLocalisationCreation(String message) {
        super("WRONG CREATION " + message);
    }
}
