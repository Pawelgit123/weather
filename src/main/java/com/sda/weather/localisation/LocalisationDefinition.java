package com.sda.weather.localisation;

import com.sda.weather.forecast.ForecastData;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class LocalisationDefinition {

    private String cityName;
    private String country;
    private String region;
    private int latitude;
    private int longitude;
    private List<ForecastData> forecastData;

    LocalisationDefinition localisationConverter(LocalisationDTO localisationDTO) {

        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        localisationDefinition.setCityName(localisationDTO.getCityName());
        localisationDefinition.setCountry(localisationDTO.getCountry());
        localisationDefinition.setRegion(localisationDTO.getRegion());
        localisationDefinition.setLatitude(localisationDTO.getLatitude());
        localisationDefinition.setLongitude(localisationDTO.getLongitude());
        localisationDefinition.setForecastData(localisationDTO.getForecastDataList());

        return localisationDefinition;
    }
}
