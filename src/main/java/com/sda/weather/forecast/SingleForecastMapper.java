package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import net.minidev.asm.ConvertDate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

//        Date date = ConvertDate.convertToDate(singleForecast.getDate());
//
//        forecastData.setDate(date);

        return forecastData;
    }

}
