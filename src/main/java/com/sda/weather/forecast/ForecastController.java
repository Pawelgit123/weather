package com.sda.weather.forecast;

import com.sda.weather.ApiConfiguration;
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
    final ForecastServiceUpdate forecastServiceUpdate;
    final LocalisationServiceGetAll localisationServiceGetAll;
    final LocalisationMapper localisationMapper;
    final ApiConfiguration apiConfiguration;

    @GetMapping("/localisation/current/{cityName}")
    ForecastData getCurrentWeatherByCityName(@PathVariable String cityName) {
        List<Localisation> localisations = localisationServiceGetAll.getAllLocalisations();

        Set<Localisation> foundLocalisation = localisations.stream()
                .filter(p -> p.getCityName().equals(cityName))
                .collect(Collectors.toSet());

        if (!foundLocalisation.isEmpty()) {
            LocalisationDTO foundLocalisationDTO = foundLocalisation.stream()
                    .map(localisationMapper::mapToLocalisationDto)
                    .findFirst()
                    .orElseThrow();

            return forecastServiceGet.getCurrentWeatherByCityName(foundLocalisationDTO);
        } else {
            throw new NotFoundException("City name not in DataBase");
        }
    }

    @GetMapping("/localisation/forecast/{cityName}")
    List<ForecastData> getForecastByCityCoordinates(@PathVariable String cityName) {
        List<Localisation> localisations = localisationServiceGetAll.getAllLocalisations();
        Set<Localisation> foundLocalisation = localisations.stream()
                .filter(p -> p.getCityName().equals(cityName))
                .collect(Collectors.toSet());

        if (!foundLocalisation.isEmpty()) {
            LocalisationDTO foundLocalisationDTO = foundLocalisation.stream()
                    .map(localisationMapper::mapToLocalisationDto)
                    .findFirst()
                    .orElseThrow();

            Localisation localisation = foundLocalisation.stream()
                    .findFirst()
                    .orElseThrow();

            forecastServiceGet.getForecastByCityName(foundLocalisationDTO);
            forecastServiceUpdate.updateForecastDateListForLocalisation(foundLocalisationDTO, localisation);

            return localisation.getForecastDataList();
        } else {
            throw new NotFoundException("City name not in DataBase");
        }
    }

    @GetMapping("/localisation/urltest/{cityName}")
    String TESTurl(@PathVariable String cityName) {
        List<Localisation> localisations = localisationServiceGetAll.getAllLocalisations();
        Localisation localisation = localisations.stream()
                .filter(p ->p.getCityName().equals(cityName))
                .findFirst()
                .orElseThrow();

        LocalisationDTO localisationDTO = localisationMapper.mapToLocalisationDto(localisation);

        String ulr = apiConfiguration.getUrlCurrent();
        String city = localisationDTO.getCityName();
        String units = apiConfiguration.getUnits();
        String apikey = apiConfiguration.getApikey();
        String urlFinal = ulr+city+units+apikey;

        return urlFinal;
    }

    @GetMapping("/localisation/test/{cityName}")
    LocalisationDTO TESTcontroller(@PathVariable String cityName) {
        List<Localisation> localisations = localisationServiceGetAll.getAllLocalisations();

        Set<Localisation> foundLocalisation = localisations.stream()
                .filter(p -> p.getCityName().equals(cityName))
                .collect(Collectors.toSet());

        if (!foundLocalisation.isEmpty()) {
            LocalisationDTO foundLocalisationDTO = foundLocalisation.stream()
                    .map(localisationMapper::mapToLocalisationDto)
                    .findFirst()
                    .orElseThrow();
            return foundLocalisationDTO;
        } else {
            return null;
        }
    }
}



