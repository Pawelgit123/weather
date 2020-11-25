package com.sda.weather.exceptions;

public class BlankSpaceException extends RuntimeException {
    public BlankSpaceException(String message) {
        super("WRONG CRETION " +message);
    }
}
