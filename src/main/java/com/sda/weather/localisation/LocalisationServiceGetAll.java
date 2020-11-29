package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalisationServiceGetAll {

    final LocalisationRepository localisationRepository;

    public List<Localisation> getAllLocalisations() {
        return localisationRepository.findAll();
    }
}
