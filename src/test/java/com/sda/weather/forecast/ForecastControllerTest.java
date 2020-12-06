package com.sda.weather.forecast;

import com.sda.weather.Configuration;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class ForecastControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocalisationRepository localisationRepository;

    ForecastDataMapper forecastDataMapper;
    Configuration configuration;

    Localisation savedLocalisation;

    @BeforeEach
    void setup() {
        localisationRepository.deleteAll();
        Localisation localisation = new Localisation();
        localisation.setCityName("Sopot");
        localisation.setRegion("Pomorskie");
        localisation.setCountry("PL");
        localisation.setLatitude(15);
        localisation.setLongitude(15);
        savedLocalisation = localisationRepository.save(localisation);
    }

    // "/localisation/{id}/forecast"

    @Test
    void getForecast_status200() throws Exception {


        Long id = savedLocalisation.getId();
        MockHttpServletRequestBuilder request = get("/localisation/" +id+"/forecast")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());


    }

    @Test
    void getForecast_status400_whenPeriodIs5() {

    }

    @Test
    void getForecast_status400_whenPeriodIsNegative() {


    }


}