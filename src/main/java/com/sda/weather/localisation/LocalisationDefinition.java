package com.sda.weather.localisation;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LocalisationDefinition {

    private String cityName;
    private String country;
    private String region;
    private int latitude;
    private int longitude;

    LocalisationDefinition localisationConverter(LocalisationDTO localisationDTO) {

        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        localisationDefinition.setCityName(localisationDTO.getCityName());
        localisationDefinition.setCountry(localisationDTO.getCountry());
        localisationDefinition.setRegion(localisationDTO.getRegion());
        localisationDefinition.setLatitude(localisationDTO.getLatitude());
        localisationDefinition.setLongitude(localisationDTO.getLongitude());

        return localisationDefinition;
    }
}
