package com.sda.weather.localisation;

import org.springframework.stereotype.Component;

@Component
public class LocalisationMapper {

    LocalisationDTO mapToLocalisation(Localisation localisation){
        LocalisationDTO localisationDTO = new LocalisationDTO();

        localisationDTO.setCityName(localisation.getCityName());
        localisationDTO.setCountry(localisation.getCountry());
        localisationDTO.setLatitude(localisation.getLatitude());
        localisationDTO.setLongitude(localisation.getLongitude());

        return localisationDTO;
    }
}
