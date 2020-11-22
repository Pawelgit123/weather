package com.sda.weather.localisation;

import org.springframework.stereotype.Component;

@Component
public class LocalisationMapper {

    LocalisationDTO mapToLocalisationDto(Localisation localisation){
        LocalisationDTO localisationDTO = new LocalisationDTO();

        localisationDTO.setId(localisation.getId());
        localisationDTO.setCityName(localisation.getCityName());
        localisationDTO.setCountry(localisation.getCountry());
        localisationDTO.setLatitude(localisation.getLatitude());
        localisationDTO.setLongitude(localisation.getLongitude());
        // todo include the region field

        return localisationDTO;
    }
}
