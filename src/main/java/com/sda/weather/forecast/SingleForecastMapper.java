package com.sda.weather.forecast;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SingleForecastMapper {

    ForecastData mapSingleForecastToForecastData(ForecastItem.SingleForecast singleForecast) {
        ForecastData forecastData = new ForecastData();

        forecastData.setWindSpeed(singleForecast.getWindSpeed());
        forecastData.setWindDirection(singleForecast.getWindDirection());
        forecastData.setAirTemperature(singleForecast.getAirTemperature());
        forecastData.setAirPressure(singleForecast.getAirPressure());
        forecastData.setAirHumidity(singleForecast.getAirHumidity());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        LocalDateTime localDate = LocalDateTime.parse(singleForecast.getDate(),formatter);
        forecastData.setLocaldatetime(localDate);

        return forecastData;
    }

}
