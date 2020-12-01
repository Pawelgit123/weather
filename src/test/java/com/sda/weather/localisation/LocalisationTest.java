package com.sda.weather.localisation;

import com.sda.weather.forecast.ForecastData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class LocalisationTest {

    LocalisationParamLimit localisationParamLimit = new LocalisationParamLimit();
    List<ForecastData> forecastDataList = new ArrayList<>();

    @Test
    void latitude_bottomBarrel() {
        Localisation localisation1 = new Localisation(1L, "Gdańsk", "Polska", "Pomorskie", -90, 15,forecastDataList);
        Localisation localisation2 = new Localisation(2L, "Gdańsk", "Polska", "Pomorskie", 0, 15, forecastDataList);
        Localisation localisation3 = new Localisation(3L, "Gdańsk", "Polska", "Pomorskie", 15, 15, forecastDataList);

        assertThat(localisation1.getLatitude()).isGreaterThanOrEqualTo(LocalisationParamLimit.LATITUDE_MIN);
        assertThat(localisation2.getLatitude()).isGreaterThanOrEqualTo(LocalisationParamLimit.LATITUDE_MIN);
        assertThat(localisation3.getLatitude()).isGreaterThanOrEqualTo(LocalisationParamLimit.LATITUDE_MIN);
    }

    @Test
    void latitude_topBarrel() {
        Localisation localisation1 = new Localisation(1L, "Gdańsk", "Polska", "Pomorskie", -15, 15, forecastDataList);
        Localisation localisation2 = new Localisation(2L, "Gdańsk", "Polska", "Pomorskie", 0, 15, forecastDataList);
        Localisation localisation3 = new Localisation(3L, "Gdańsk", "Polska", "Pomorskie", 90, 15, forecastDataList);

        assertThat(localisation1.getLatitude()).isLessThanOrEqualTo(LocalisationParamLimit.LATITUDE_MAX);
        assertThat(localisation2.getLatitude()).isLessThanOrEqualTo(LocalisationParamLimit.LATITUDE_MAX);
        assertThat(localisation3.getLatitude()).isLessThanOrEqualTo(LocalisationParamLimit.LATITUDE_MAX);
    }

    @Test
    void longitude_bottomBarrel() {
        Localisation localisation1 = new Localisation(1L, "Gdańsk", "Polska", "Pomorskie", 15, -180, forecastDataList);
        Localisation localisation2 = new Localisation(2L, "Gdańsk", "Polska", "Pomorskie", 15, 0,forecastDataList);
        Localisation localisation3 = new Localisation(3L, "Gdańsk", "Polska", "Pomorskie", 15, 15, forecastDataList);

        assertThat(localisation1.getLongitude()).isGreaterThanOrEqualTo(LocalisationParamLimit.LONGITUDE_MIN);
        assertThat(localisation2.getLongitude()).isGreaterThanOrEqualTo(LocalisationParamLimit.LONGITUDE_MIN);
        assertThat(localisation3.getLongitude()).isGreaterThanOrEqualTo(LocalisationParamLimit.LONGITUDE_MIN);
    }

    @Test
    void longitude_topBarrel() {
        Localisation localisation1 = new Localisation(1L, "Gdańsk", "Polska", "Pomorskie", 15, 15, forecastDataList);
        Localisation localisation2 = new Localisation(2L, "Gdańsk", "Polska", "Pomorskie", 15, 0, forecastDataList);
        Localisation localisation3 = new Localisation(3L, "Gdańsk", "Polska", "Pomorskie", 15, 180,forecastDataList);

        assertThat(localisation1.getLongitude()).isLessThanOrEqualTo(LocalisationParamLimit.LONGITUDE_MAX);
        assertThat(localisation2.getLongitude()).isLessThanOrEqualTo(LocalisationParamLimit.LONGITUDE_MAX);
        assertThat(localisation3.getLongitude()).isLessThanOrEqualTo(LocalisationParamLimit.LONGITUDE_MAX);
    }
}
