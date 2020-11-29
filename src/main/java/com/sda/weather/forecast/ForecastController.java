package com.sda.weather.forecast;

import com.sda.weather.exceptions.NotFoundException;
import com.sda.weather.localisation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ForecastController {

    final ForecastServiceGet forecastServiceGet;
    final LocalisationServiceGetAll localisationServiceGetAll;
    final LocalisationMapper localisationMapper;

    // dla current nie ma być listy

    @GetMapping("/localisation/current/{cityName}")
    List<ForecastData> getForecastByCityName(@PathVariable String cityName) {
        List<Localisation> localisations = localisationServiceGetAll.getAllLocalisations();
        Set<LocalisationDTO> foundLocalisationDTO = localisations.stream()
                .filter(p -> p.getCityName().equals(cityName))
                .map(localisationMapper::mapToLocalisationDto)
                .collect(Collectors.toSet());
        if (!foundLocalisationDTO.isEmpty()) {
            LocalisationDTO localisationDTO = foundLocalisationDTO.stream().findFirst().orElseThrow();

            forecastServiceGet.getCurrentWeatherByCityName(localisationDTO);

            // drugi DTO ma zaktualizować liste w localisation?

            return foundLocalisationDTO.stream()
                    .findFirst()
                    .get().getForecastDataList();

            // pierwszy DTO jest do sprawdzenia a drugi ma mieć już zaaktualizowane dane?

        } else {
            throw new NotFoundException("City name not in DataBase");
        }
    }


}
