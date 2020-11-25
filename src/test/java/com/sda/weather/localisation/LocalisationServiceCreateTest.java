package com.sda.weather.localisation;

import com.sda.weather.exceptions.BadLocalisationCreation;
import com.sda.weather.exceptions.BlankSpaceException;
import com.sda.weather.exceptions.DataOutOfBound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LocalisationServiceCreateTest {

    @Autowired
    LocalisationRepository localisationRepository;

    @InjectMocks
    LocalisationServiceCreate localisationServiceCreate;

    @BeforeEach
    void setup() {
        localisationRepository.deleteAll();
    }

    @Test
    void localisationCreate_MissingCityName() {
        LocalisationDTO localisationDTO = new LocalisationDTO(null, "", "Polska", "Pomorskie", 15, 15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(() -> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(BadLocalisationCreation.class);
    }

    @Test
    void localisationCreate_MissingCountry() {
        LocalisationDTO localisationDTO = new LocalisationDTO(null, "Gdańsk", "", "Pomorskie", 15, 15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(() -> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(BadLocalisationCreation.class);
    }

    @Test
    void localisationCreate_RegionOptional() {
        LocalisationDTO localisationDTO = new LocalisationDTO(null, "Gdańsk", "Polskae", "", 15, 15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(() -> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isNotExactlyInstanceOf(BadLocalisationCreation.class);
    }

    @Test
    void localisationCreate_latitudeOutOfBound() {
        LocalisationDTO localisationDTO = new LocalisationDTO(null, "Gdańsk", "Polska", "Pomorskie", 91, 15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(() -> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(DataOutOfBound.class);
    }

    @Test
    void localisationCreate_longitudeOutOfBound() {
        LocalisationDTO localisationDTO = new LocalisationDTO(null, "Gdańsk", "Polska", "Pomorskie", 15, 181);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(() -> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(DataOutOfBound.class);
    }

    @Test
    void localisationCreate_cityNameIsBlank() {
        LocalisationDTO localisationDTO = new LocalisationDTO(null, "  ", "asd", "asd", 15, 15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(() -> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(BlankSpaceException.class);
    }

    @Test
    void localisationCreate_countryIsBlank() {
        LocalisationDTO localisationDTO = new LocalisationDTO(null, "asd", "   ", "asd", 15, 15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(() -> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(BlankSpaceException.class);
    }
    @Test
    void localisationCreate_regionIsBlank() {
        LocalisationDTO localisationDTO = new LocalisationDTO(null, "asd", "asd", "   ", 15, 15);
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        LocalisationDefinition convertedLocalisation = localisationDefinition.localisationConverter(localisationDTO);

        Throwable result = catchThrowable(() -> localisationServiceCreate.createLocalisation(convertedLocalisation));

        assertThat(result).isExactlyInstanceOf(BlankSpaceException.class);
    }
}
