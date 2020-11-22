package com.sda.weather.localisation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exceptions.BadLocalisationCreation;
import com.sda.weather.exceptions.BlankSpaceException;
import com.sda.weather.exceptions.DataOutOfBound;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpResponse;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LocalisationServiceCreateTest {

    @Autowired
    LocalisationRepository localisationRepository;

    @InjectMocks
    LocalisationServiceCreate localisationServiceCreate;

    @BeforeEach
    void setup(){
        localisationRepository.deleteAll();
    }

    @Test
    void localisationCreate_MissingCityName() {
        LocalisationDTO localisationDTO = new LocalisationDTO(
                "","Polska","Pomorskie",15,15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(()-> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(BadLocalisationCreation.class);
    }

    @Test
    void localisationCreate_MissingCountry() {
        LocalisationDTO localisationDTO = new LocalisationDTO(
                "Gdańsk","","Pomorskie",15,15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(()-> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(BadLocalisationCreation.class);
    }
    @Test
    void localisationCreate_RegionOptional() {
        LocalisationDTO localisationDTO = new LocalisationDTO(
                "Gdańsk","Polskae","",15,15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(()-> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isNotExactlyInstanceOf(BadLocalisationCreation.class);
    }
    @Test
    void localisationCreate_latitudeOutOfBound() {
        LocalisationDTO localisationDTO = new LocalisationDTO(
                "Gdańsk","Polska","Pomorskie",91,15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(()-> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(DataOutOfBound.class);
    }
    @Test
    void localisationCreate_longitudeOutOfBound() {
        LocalisationDTO localisationDTO = new LocalisationDTO(
                "Gdańsk","Polska","Pomorskie",15,181);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(()-> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(DataOutOfBound.class);
    }

    @Test
    void localisationCreate_cityNameIsBlank() {
        LocalisationDTO localisationDTO = new LocalisationDTO(
                "  ","Polska","Pomorskie",15,15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(()-> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(BlankSpaceException.class);
    }

}