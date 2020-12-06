package com.sda.weather.forecast;

import org.springframework.stereotype.Component;

@Component
public class ForecastDataMapper {

    ForecastDataDTO mapForecastDataToForecastDataDTO(ForecastData forecastData) {
        ForecastDataDTO forecastDataDTO = new ForecastDataDTO();

        forecastDataDTO.setAirHumidity(forecastData.getAirHumidity());
        forecastDataDTO.setAirPressure(forecastData.getAirPressure());
        forecastDataDTO.setAirTemperature(forecastData.getAirTemperature());
        forecastDataDTO.setWindDirection(forecastData.getWindDirection());
        forecastDataDTO.setWindSpeed(forecastData.getWindSpeed());
        forecastDataDTO.setLocalisation(forecastData.getLocalisation());
        forecastData.setDate(forecastData.getDate());

        return forecastDataDTO;

    }
}
