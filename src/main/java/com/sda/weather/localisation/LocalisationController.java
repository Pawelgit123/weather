package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocalisationController {

    final LocalisationServiceCreate localisationServiceCreate;
    final LocalisationServiceFind localisationServiceFind;
    final LocalisationServiceGetAll localisationServiceGetAll;
    final LocalisationMapper localisationMapper;
    final LocalisationDefinition localisationDefinition;

    @GetMapping("/localisation/{id}")
    LocalisationDTO getLocalisationByID(@PathVariable Long id) {
        Localisation localisation = localisationServiceFind.findLocalisationByID(id);
        LocalisationDTO localisationDTO = localisationMapper.mapToLocalisation(localisation);
        return localisationDTO;
    }

    @GetMapping("/localisation/")
    List<Localisation> getLocalisations() {

        List<Localisation> localisations = localisationServiceGetAll.getAllLocalisations();
        // czy ma zwracać liste DTO i potrzebny jest nowy mapper?

        return localisations;
    }

    @PostMapping("localisation")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<LocalisationDTO> createLocalisation(@RequestBody LocalisationDTO localisationDTO) {
        LocalisationDefinition converterdLocalisation = localisationDefinition.localisationConverter(localisationDTO);
        Localisation localisation = localisationServiceCreate.createLocalisation(converterdLocalisation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localisationMapper.mapToLocalisation(localisation));
    }

    // todo LocalisationDTO -> LocalisationDefinition -> pass to a service -> Localisation -> LocalisationDTO
}
