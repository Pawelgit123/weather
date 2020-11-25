package com.sda.weather.localisation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class LocalisationServiceFindIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalisationRepository localisationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        localisationRepository.deleteAll();
    }

    @Test
    void localisationServiceFind_whenIdIsMissing_returns404StatusCode() throws Exception {
        // given
        MockHttpServletRequestBuilder builder = get("/localisation/1")
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(builder).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void localisationServiceFind_returnSavedLocation() throws Exception {
        // given
        Localisation localisation = new Localisation();
        localisation.setCityName("Gdansk");
        localisation.setCountry("Poland");
        localisation.setLatitude(0);
        localisation.setLongitude(1);
        localisation.setRegion("Pomorskie");
        Localisation savedLocation = localisationRepository.save(localisation);
        Long id = savedLocation.getId();
        MockHttpServletRequestBuilder builder = get("/localisation/" + id)
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MvcResult result = mockMvc.perform(builder).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        LocalisationDTO responseBody = objectMapper.readValue(response.getContentAsString(), LocalisationDTO.class);
        assertThat(responseBody.getId()).isEqualTo(id);
        assertThat(responseBody.getCityName()).isEqualTo("Gdansk");
        assertThat(responseBody.getCountry()).isEqualTo("Poland");
        assertThat(responseBody.getLatitude()).isEqualTo(0);
        assertThat(responseBody.getLongitude()).isEqualTo(1);
        assertThat(responseBody.getRegion()).isEqualTo("Pomorskie");
    }
}
