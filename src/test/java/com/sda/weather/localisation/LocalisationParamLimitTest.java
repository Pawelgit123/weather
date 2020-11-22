package com.sda.weather.localisation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalisationParamLimitTest {


    @Test
    void getLatitudeMAX() {
        LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();
        assertEquals(90,localisationParamLimit.getLatitudeMAX());
    }

    @Test
    void getLatitudeMIN() {
        LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();
        assertEquals(-90,localisationParamLimit.getLatitudeMIN());
    }

    @Test
    void getLongitudeMAX() {
        LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();
        assertEquals(180,localisationParamLimit.getLongitudeMAX());
    }

    @Test
    void getLongitudeMIN() {
        LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();
        assertEquals(-180,localisationParamLimit.getLongitudeMIN());
    }
}