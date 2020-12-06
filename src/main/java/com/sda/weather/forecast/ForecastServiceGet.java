package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.ApiConfiguration;
import com.sda.weather.exceptions.ForecastAPiFailure;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationRepository;
import com.sda.weather.localisation.LocalisationServiceFind;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForecastServiceGet {

    private final LocalisationServiceFind localisationServiceFind;
    private final ApiConfiguration apiConfiguration;
    private final SingleForecastMapper singleForecastMapper;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

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
                .queryParam("appid", apiConfiguration.getApikey())
                .build()
                .toString();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new ForecastAPiFailure("Server fail");
        }
        String responseBody = responseEntity.getBody();
        try {

            ForecastItem forecastItem = objectMapper.readValue(responseBody, ForecastItem.class);

//            "\"dt_txt\": \"2020-12-06 21:00:00\"

            LocalDate now = LocalDate.now();
            LocalDate forecastDateFromPeriod = now.plusDays(period);
            LocalDateTime forecastDateFromPeriodAtTwelve = forecastDateFromPeriod.atTime(12, 0);

            // .atTime(...) 12:00
            //instant.now()

            List<ForecastItem.SingleForecast> singleForecastList = forecastItem.getSingleForecastList();
            List<ForecastData> collect = singleForecastList.stream()
                    .map(singleForecastMapper::mapSingleForecastToForecastData)
                    .collect(Collectors.toList());

            ForecastData forecastData = collect.stream()
                    .filter(p -> p.getLocaldatetime().equals(forecastDateFromPeriodAtTwelve))
                    .findFirst()
                    .orElseThrow();

            List<ForecastData> forecastDataList = localisation.getForecastDataList();
            forecastDataList.add(forecastData);
            forecastRepository.save(forecastData);


//            switch (period){
//                case 1:
//
//                    singleForecastList.stream()
//                            .map(p -> LocalDate.parse(p.getDate(),formatter))
//                            .filter(p -> p.equals(forecastDate));
//
//
//                    break;
//
//                case 2:
//                     break;
//                case 3:
//                    break;
//                case 4:
//                    break;
//                case 5:
//                    break;
//            }

            return forecastData;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

}
