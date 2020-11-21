package com.sda.weather.localisation;

import com.sda.weather.exceptions.BadLocalisationCreation;
import com.sda.weather.exceptions.DataOutOfBound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalisationServiceCreate {

    final LocalisationRepository localisationRepository;

    Localisation createLocalisation (LocalisationDefinition localisationDefinition) {

        if (localisationDefinition.getCityName().isEmpty()) {
            throw new BadLocalisationCreation("City is empty");
        }
        if (localisationDefinition.getCountry().isEmpty()) {
            throw new BadLocalisationCreation("Country is empty");
        }
        if (localisationDefinition.getRegion().isEmpty()) {
            throw new BadLocalisationCreation("Region is empty");
        }
        if (localisationDefinition.getLatitude()>90 && localisationDefinition.getLatitude()<-90){
            throw new DataOutOfBound("Latitude out of bound");
        }
        if (localisationDefinition.getLongitude()>180 && localisationDefinition.getLongitude()<-180){
            throw new DataOutOfBound("Latitude out of bound");
        }

        Localisation localisation = new Localisation();
        localisation.setCityName(localisationDefinition.getCityName());
        localisation.setCountry(localisationDefinition.getCountry());
        localisation.setRegion(localisationDefinition.getRegion());
        localisation.setLatitude(localisationDefinition.getLatitude());
        localisation.setLongitude(localisation.getLongitude());

        return localisationRepository.save(localisation);
    }
}
