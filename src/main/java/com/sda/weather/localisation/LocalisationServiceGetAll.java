package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocalisationServiceGetAll {

    final LocalisationRepository localisationRepository;

    List<Localisation> getAllLocalisations(){

        List<Localisation> localisations = localisationRepository.findAll();

        return localisations;
    }
}
