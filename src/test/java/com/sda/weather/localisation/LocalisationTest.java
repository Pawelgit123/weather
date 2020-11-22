package com.sda.weather.localisation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class LocalisationTest {


    @Test
    void latitude_bottomBarrel() {
        LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();

        Localisation localisation1 = new Localisation(1L, "Gdańsk", "Polska", "Pomorskie", -90, 15);
        Localisation localisation2 = new Localisation(2L, "Gdańsk", "Polska", "Pomorskie", 0, 15);
        Localisation localisation3 = new Localisation(3L, "Gdańsk", "Polska", "Pomorskie", 15, 15);

        assertThat(localisation1.getLatitude()).isGreaterThanOrEqualTo(localisationParamLimit.getLatitudeMIN());
        assertThat(localisation2.getLatitude()).isGreaterThanOrEqualTo(localisationParamLimit.getLatitudeMIN());
        assertThat(localisation3.getLatitude()).isGreaterThanOrEqualTo(localisationParamLimit.getLatitudeMIN());
    }

    @Test
    void latitude_topBarrel() {
        LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();

        Localisation localisation1 = new Localisation(1L, "Gdańsk", "Polska", "Pomorskie", -15, 15);
        Localisation localisation2 = new Localisation(2L, "Gdańsk", "Polska", "Pomorskie", 0, 15);
        Localisation localisation3 = new Localisation(3L, "Gdańsk", "Polska", "Pomorskie", 90, 15);

        assertThat(localisation1.getLatitude()).isLessThanOrEqualTo(localisationParamLimit.getLatitudeMAX());
        assertThat(localisation2.getLatitude()).isLessThanOrEqualTo(localisationParamLimit.getLatitudeMAX());
        assertThat(localisation3.getLatitude()).isLessThanOrEqualTo(localisationParamLimit.getLatitudeMAX());
    }

    @Test
    void longitue_bottomBarrel() {
        LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();

        Localisation localisation1 = new Localisation(1L, "Gdańsk", "Polska", "Pomorskie", 15, -180);
        Localisation localisation2 = new Localisation(2L, "Gdańsk", "Polska", "Pomorskie", 15, 0);
        Localisation localisation3 = new Localisation(3L, "Gdańsk", "Polska", "Pomorskie", 15, 15);

        assertThat(localisation1.getLongitude()).isGreaterThanOrEqualTo(localisationParamLimit.getLongitudeMIN());
        assertThat(localisation2.getLongitude()).isGreaterThanOrEqualTo(localisationParamLimit.getLongitudeMIN());
        assertThat(localisation3.getLongitude()).isGreaterThanOrEqualTo(localisationParamLimit.getLongitudeMIN());
    }

    @Test
    void longitudee_topBarrel() {
        LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();

        Localisation localisation1 = new Localisation(1L, "Gdańsk", "Polska", "Pomorskie", 15, 15);
        Localisation localisation2 = new Localisation(2L, "Gdańsk", "Polska", "Pomorskie", 15, 0);
        Localisation localisation3 = new Localisation(3L, "Gdańsk", "Polska", "Pomorskie", 15, 180);

        assertThat(localisation1.getLongitude()).isLessThanOrEqualTo(localisationParamLimit.getLongitudeMAX());
        assertThat(localisation2.getLongitude()).isLessThanOrEqualTo(localisationParamLimit.getLongitudeMAX());
        assertThat(localisation3.getLongitude()).isLessThanOrEqualTo(localisationParamLimit.getLongitudeMAX());
    }

}