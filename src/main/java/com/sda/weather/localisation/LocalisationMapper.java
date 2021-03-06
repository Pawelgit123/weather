package com.sda.weather.localisation;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LocalisationMapper {

    LocalisationDTO mapToLocalisationDto(Localisation localisation) {
        LocalisationDTO localisationDTO = new LocalisationDTO();

        localisationDTO.setId(localisation.getId());
        localisationDTO.setCityName(localisation.getCityName());
        localisationDTO.setCountry(localisation.getCountry());
        localisationDTO.setLatitude(localisation.getLatitude());
        localisationDTO.setLongitude(localisation.getLongitude());
        localisationDTO.setRegion(localisation.getRegion().orElse(null));


        return localisationDTO;
    }
}
