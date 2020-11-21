package com.sda.weather.localisation;

import lombok.Data;

@Data
public class LocalisationDefinition {

    String cityName;
    String country;
    String region;
    int latitude;
    int longitude;

    LocalisationDefinition localisationConverter(LocalisationDTO localisationDTO){

        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        this.cityName = localisationDTO.getCityName();
        this.country = localisationDTO.getCountry();
        this.region = localisationDTO.getRegion();
        this.latitude = localisationDTO.getLatitude();
        this.longitude = localisationDTO.getLongitude();

        return localisationDefinition;
    }
}
