package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    List<LocalisationDTO> getLocalisations() {

        List<Localisation> localisations = localisationServiceGetAll.getAllLocalisations();
        List<LocalisationDTO> dtos = new ArrayList<>();
        localisations.forEach(p -> dtos.add(localisationMapper.mapToLocalisation(p)));

        return dtos;
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
