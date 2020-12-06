package com.sda.weather.localisation;

import com.sda.weather.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalisationServiceFind {

    final LocalisationRepository localisationRepository;

    public Localisation findLocalisationByID(Long id) {
        return localisationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("id of an entry: " + id));
    }
}
