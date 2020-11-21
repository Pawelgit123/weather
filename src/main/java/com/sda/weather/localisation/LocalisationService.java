package com.sda.weather.localisation;

import com.sda.weather.exceptions.BadLocalisationCreation;
import com.sda.weather.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalisationService {

    final LocalisationRepository localisationRepository;

    Localisation createLocalisation(String cityName, String cityCountry) throws BadLocalisationCreation {
        if(cityName.isEmpty()){
            throw new BadLocalisationCreation("city name is empty");
        }

        Localisation localisation = new Localisation();
        localisation.setCityName(cityName);
        localisation.setCountry(cityCountry);

        return localisationRepository.save(localisation);
    }

    Localisation findLocalisationByID(Long id){
        return localisationRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("ID: " +id));

    }

}
