package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SingleForecastMapper {

    ForecastData mapSingleForecastToForecastData(ForecastItem.SingleForecast singleForecast) {
        ForecastData forecastData = new ForecastData();

        forecastData.setWindSpeed(singleForecast.getWindSpeed());
        forecastData.setWindDirection(singleForecast.getWindDirection());
        forecastData.setAirTemperature(singleForecast.getAirTemperature());
        forecastData.setAirPressure(singleForecast.getAirPressure());
        forecastData.setAirHumidity(singleForecast.getAirHumidity());

        return forecastData;
    }

}
