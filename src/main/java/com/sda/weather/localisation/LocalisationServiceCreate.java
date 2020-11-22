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
        if (localisationDefinition.getLongitude() <= LocalisationParamLimit.LONGITUDE_MAX
                || localisationDefinition.getLongitude() >= LocalisationParamLimit.LONGITUDE_MIN) {
            throw new DataOutOfBound("Longitude out of bound");
        }
        if (localisationDefinition.getCityName().isBlank()) {
            throw new BlankSpaceException("Blank spaces in <name>");
        }
        if (localisationDefinition.getCountry().isBlank()) {
            throw new BlankSpaceException("Blank spaces in <country>");
        }
        if (localisationDefinition.getRegion().isBlank()) { // todo remove this validation
            throw new BlankSpaceException("Blank spaces in <region>");
        }

        Localisation localisation = new Localisation();
        localisation.setCityName(localisationDefinition.getCityName());
        localisation.setCountry(localisationDefinition.getCountry());
        localisation.setRegion(localisationDefinition.getRegion());     // todo if it is blank then don't save it
        localisation.setLatitude(localisationDefinition.getLatitude());
        localisation.setLongitude(localisation.getLongitude());

        return localisationRepository.save(localisation);
    }
}
