package com.sda.weather.localisation;

import lombok.Data;

@Data
public class LocalisationDefinition {

    private String cityName;
    private String country;
    private String region;
    private int latitude;
    private int longitude;

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
