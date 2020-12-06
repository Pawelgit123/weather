package com.sda.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastItem {

    private String cod;

    private List<SingleForecast> singleForecastList;
    private CityResponse cityRespones;

    @Data
    public static class SingleForecast {
        @JsonProperty("temp")
        private int airTemperature;
        @JsonProperty("pressure")
        private int airPressure;
        @JsonProperty("humidity")
        private int airHumidity;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("deg")
        private String windDirection;
        @JsonProperty("dt_txt")
        private String date;
    }

    @Data
    public static class CityResponse {
        private String name;

    }
}