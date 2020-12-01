package com.sda.weather.localisation;

import com.sda.weather.forecast.ForecastData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDTO {

    Long id;
    String cityName;
    String country;
    String region;
    float latitude;
    float longitude;
    List<ForecastData> forecastDataList;
}
