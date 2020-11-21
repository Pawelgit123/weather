package com.sda.weather.exceptions;

public class BadLocalisationCreation extends Throwable {

    public BadLocalisationCreation(String message) {
        super("WRONG CREATION" + message);
    }
}
