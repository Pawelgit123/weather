package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.ApiConfiguration;
import com.sda.weather.exceptions.ForecastAPiFailure;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForecastServiceGet {

    private ApiConfiguration apiConfiguration;
    private ForecastDataMapper forecastDataMapper;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();


    ForecastItem getCurrentWeatherByCityName(LocalisationDTO localisationDTO) {
// http://api.openweathermap.org/data/2.5/find?q=Gdansk&units=metric&appid=a5bd02ecf7c1f72449ae4d087d08d275
        String ulr = apiConfiguration.getUrl();
        String city = localisationDTO.getCityName();
        String units = apiConfiguration.getUnits();
        String apikey = apiConfiguration.getApikey();
        String urlFinal = ulr+city+units+apikey;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlFinal, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new ForecastAPiFailure("Server fail");
        }
        String responseBody = responseEntity.getBody();
        try {
            ForecastItem forecastItem = objectMapper.readValue(responseBody, ForecastItem.class);
            List<ForecastData> forecastDataList = localisationDTO.getForecastDataList();
            forecastDataList.add(forecastDataMapper.mapToForecastData(forecastItem));


            return forecastItem;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=hourly,minutely&appid=a5bd02ecf7c1f72449ae4d087d08d275
    // daily chicago

    // https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&units=metric&exclude=hourly,minutely&appid=a5bd02ecf7c1f72449ae4d087d08d275
    // metric sys


}
