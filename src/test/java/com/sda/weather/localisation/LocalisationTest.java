package com.sda.weather.localisation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class LocalisationTest {

    private Localisation localisation;
    private LocalisationParamLimit localisationParamLimit;


    @BeforeEach
    public void init() {


        List<Localisation> localisations = List.of(new Localisation());

        // aaaaaaaaaaaaa nie wiem jak łatwo zrobić obiekty z różnymi parametrami, chce tylko 2 przetestować

    }

    @Test
    void latitude_bottomBarrel() {
        int bottomLine = localisationParamLimit.getLatitudeMIN();
        assertThat(localisation.getLatitude()).isGreaterThanOrEqualTo(bottomLine);
    }

    @Test
    void latitude_topBarrel() {
        int topline = localisationParamLimit.getLatitudeMAX();
        assertThat(localisation.getLatitude()).isLessThanOrEqualTo(topline);
    }

    @Test
    void longitue_bottomBarrel() {
        int bottomLine = localisationParamLimit.getLongitudeMIN();
        assertThat(localisation.getLongitude()).isGreaterThanOrEqualTo(bottomLine);
    }

    @Test
    void longitudee_topBarrel() {
        int topline = localisationParamLimit.getLongitudeMIN();
        assertThat(localisation.getLongitude()).isLessThanOrEqualTo(topline);
    }

}