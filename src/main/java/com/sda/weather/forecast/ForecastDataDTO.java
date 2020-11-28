package com.sda.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastDataDTO {

    int id;
    int airTemperature;
    int airPressure;
    int airHumidity;
    String windDirection;
    int windSpeed;

}
