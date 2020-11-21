package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocalisationController {

    final LocalisationService localisationService;

    @GetMapping("/localisation/{id}")
    LocalisationDTO getLocalisationByID(@PathVariable Long id) {
        Localisation localisation = localisationService.findLocalisationByID(id);
        // todo use a mapper to convert data
        return null;
    }

    // todo create endpoint for creation
    // todo LocalisationDTO -> LocalisationDefinition -> pass to a service -> Localisation -> LocalisationDTO
}
