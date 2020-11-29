package com.sda.weather.forecast;

public class ForecastDataMapper {

    ForecastData mapToForecastData(ForecastItem forecastItem) {
        ForecastData forecastData = new ForecastData();

        forecastData.setAirHumidity(forecastItem.getAirHumidity());
        forecastData.setAirPressure(forecastItem.getAirPressure());
        forecastData.setAirTemperature(forecastItem.getAirTemperature());
        forecastData.setWindDirection(forecastItem.getWindDirection());
        forecastData.setWindSpeed(forecastItem.getWindSpeed());

        return forecastData;

    }
}
