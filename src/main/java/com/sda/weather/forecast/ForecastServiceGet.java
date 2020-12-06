package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.Configuration;
import com.sda.weather.exceptions.ForecastAPiFailure;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationDTO;
import com.sda.weather.localisation.LocalisationServiceFind;
import com.sda.weather.localisation.LocalisationServiceGetAll;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    // todo write some test :) we need it to develop our code quickly

    ForecastData getForecast(Long id, String period) {

        // api.openweathermap.org/data/2.5/forecast?q={city name}&appid={API key}

        Localisation localisation = localisationServiceFind.findLocalisationByID(id);

        String units = "metric";

        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam(localisation.getCityName())
                .queryParam(configuration.getApikey())
                .queryParam(units)
                .build()
                .toString();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new ForecastAPiFailure("Server fail");
        }
        String responseBody = responseEntity.getBody();
        try {

            ForecastItem forecastItem = objectMapper.readValue(responseBody, ForecastItem.class);


            List<ForecastItem.SingleForecast> singleForecastList = forecastItem.getSingleForecastList();

            // sprawdzić period z każdego singleforecastlist

//            List<ForecastData> collect = singleForecastList.stream()
//                    .map(singleForecastMapper::mapSingleForecastToForecastData)
//                    .forEach(p -> localisation.getForecastDataList().add(p))
//                    .collect

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return new ForecastData();
    }

}
