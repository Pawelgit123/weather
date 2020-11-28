package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.ApiConfiguration;
import com.sda.weather.exceptions.ForecastAPiFailure;
import com.sda.weather.localisation.Localisation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private ApiConfiguration apiConfiguration;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    List<ForecastItem> list;


    public ForecastItem getForecast(Localisation localisation) {
// http://api.openweathermap.org/data/2.5/find?q=Gdansk&units=metric&appid=a5bd02ecf7c1f72449ae4d087d08d275
        String ulr = apiConfiguration.getUrl();
        String city = localisation.getCityName();
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




            return forecastItem;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
