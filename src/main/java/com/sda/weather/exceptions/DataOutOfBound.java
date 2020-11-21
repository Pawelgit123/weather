package com.sda.weather.exceptions;

public class DataOutOfBound extends RuntimeException {
    public DataOutOfBound(String message) {
        super("WRONG DATAd " + message);
    }
}
