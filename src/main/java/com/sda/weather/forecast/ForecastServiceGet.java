package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.Configuration;
import com.sda.weather.exceptions.ForecastAPiFailure;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationRepository;
import com.sda.weather.localisation.LocalisationServiceFind;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForecastServiceGet {

    private final LocalisationServiceFind localisationServiceFind;
    private final Configuration configuration;
    private final SingleForecastMapper singleForecastMapper;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ForecastRepository forecastRepository;
    private final LocalisationRepository localisationRepository;

    // todo write some test :) we need it to develop our code quickly

    ForecastData getForecast(Long id, Integer period) {
        // api.openweathermap.org/data/2.5/forecast?q={city name}&appid={API key}
        Localisation localisation = localisationServiceFind.findLocalisationByID(id);

        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", localisation.getCityName())
                .queryParam("units", "metric")
                .queryParam("appid", configuration.getApikey())
                .build()
                .toString();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new ForecastAPiFailure("Server fail");
        }
        String responseBody = responseEntity.getBody();
        try {

            ForecastItem forecastItem = objectMapper.readValue(responseBody, ForecastItem.class);

            LocalDateTime now = LocalDateTime.now();                // todo use LocalDate.now()
            LocalDateTime forecastDate = now.plusDays(period);
                                                                    // .atTime(...) 12:00

            // tymczasowo:

            List<ForecastItem.SingleForecast> singleForecastList = forecastItem.getSingleForecastList();
            List<ForecastData> collect = singleForecastList.stream()
                    .map(singleForecastMapper::mapSingleForecastToForecastData)
                    .collect(Collectors.toList());

            ForecastData forecastData = collect.stream().findFirst().orElseThrow();
            List<ForecastData> forecastDataList = localisation.getForecastDataList();
            forecastDataList.add(forecastData);
            forecastData.setLocalisation(localisation);
            forecastRepository.save(forecastData);
            localisationRepository.save(localisation);

            return forecastData;


//            switch (period){
//                case "1":
//                    Set<ForecastData> collect = singleForecastList.stream()
//                            .map(singleForecastMapper::mapSingleForecastToForecastData)
//                            .filter()
//                            .collect(Collectors.toList());
//                    break;
//
//                case "2":
//                     break;
//                case "3":
//                    break;
//                case "4":
//                    break;
//                case "5":
//                    break;
//            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

}
