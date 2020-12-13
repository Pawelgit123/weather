package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        LocalisationDTO localisationDTO = localisationMapper.mapToLocalisationDto(localisation);
        return localisationDTO;
    }

    @GetMapping("/localisation")
    List<LocalisationDTO> getLocalisations() {
        return localisationServiceGetAll.getAllLocalisations().stream()
                .map(localisationMapper::mapToLocalisationDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/localisation")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<LocalisationDTO> createLocalisation(@RequestBody LocalisationDTO localisationDTO) {
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);
        Localisation localisation = localisationServiceCreate.createLocalisation(convertedLocalisation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localisationMapper.mapToLocalisationDto(localisation));
    }
}
