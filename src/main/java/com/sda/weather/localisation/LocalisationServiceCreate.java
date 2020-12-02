package com.sda.weather.localisation;

import com.sda.weather.exceptions.BadLocalisationCreation;
import com.sda.weather.exceptions.BlankSpaceException;
import com.sda.weather.exceptions.DataOutOfBound;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalisationServiceCreate {

    final LocalisationRepository localisationRepository;

    Localisation createLocalisation(LocalisationDefinition localisationDefinition) {
        if (localisationDefinition.getCityName().isEmpty()) {
            throw new BadLocalisationCreation("City is empty");
        }
        if (localisationDefinition.getCountry().isEmpty()) {
            throw new BadLocalisationCreation("Country is empty");
        }
        if (localisationDefinition.getLatitude() <= LocalisationParamLimit.LATITUDE_MIN
                || localisationDefinition.getLatitude() >= LocalisationParamLimit.LATITUDE_MAX) {
            throw new DataOutOfBound("Latitude out of bound");
        }
        if (localisationDefinition.getCityName().isBlank()) {
            throw new BlankSpaceException("Blank spaces in <name>");
        }
        if (localisationDefinition.getCountry().isBlank()) {
            throw new BlankSpaceException("Blank spaces in <country>");
        }
        // zamieniłem miejscami warunki i testy nagle działają? jaki jest priorytet wyjątków?

        if (localisationDefinition.getRegion().isBlank()) {
            throw new BlankSpaceException("Blank spaces in <region>");
        }

        if (localisationDefinition.getLongitude() <= LocalisationParamLimit.LONGITUDE_MIN
                || localisationDefinition.getLongitude() >= LocalisationParamLimit.LONGITUDE_MAX) {
            throw new DataOutOfBound("Longitude out of bound");
        }

        Localisation localisation = new Localisation();
        localisation.setCityName(localisationDefinition.getCityName());
        localisation.setCountry(localisationDefinition.getCountry());
        if (!localisationDefinition.getRegion().isBlank()) {
            localisation.setRegion(localisationDefinition.getRegion());
        }
        localisation.setLatitude(localisationDefinition.getLatitude());
        localisation.setLongitude(localisationDefinition.getLongitude());

        return localisationRepository.save(localisation);
    }
}
