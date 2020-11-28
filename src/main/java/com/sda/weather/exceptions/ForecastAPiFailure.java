package com.sda.weather.exceptions;

public class ForecastAPiFailure extends RuntimeException {
    public ForecastAPiFailure(String message) {
        super("Forecast API:" + message);
    }
}
