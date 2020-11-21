package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocalisationController {

    final LocalisationServiceCreate localisationServiceCreate;
    final LocalisationServiceFind localisationServiceFind;
    final LocalisationMapper localisationMapper;
    final LocalisationDefinition localisationDefinition;

    @GetMapping("/localisation/{id}")
    LocalisationDTO getLocalisationByID(@PathVariable Long id) {
        Localisation localisation = localisationServiceFind.findLocalisationByID(id);
        LocalisationDTO localisationDTO = localisationMapper.mapToLocalisation(localisation);
        return localisationDTO;
    }
    @PostMapping("localisation")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<LocalisationDTO> createLocalisation(@RequestBody LocalisationDTO localisationDTO){
        LocalisationDefinition converterdLocalisation = localisationDefinition.localisationConverter(localisationDTO);
        Localisation localisation = localisationServiceCreate.createLocalisation(converterdLocalisation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localisationMapper.mapToLocalisation(localisation));
    }

    // todo create endpoint for creation
    // todo LocalisationDTO -> LocalisationDefinition -> pass to a service -> Localisation -> LocalisationDTO
}
