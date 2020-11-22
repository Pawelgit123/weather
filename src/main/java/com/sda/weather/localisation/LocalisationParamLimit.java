package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocalisationParamLimit {

    private final int latitudeMAX = 90;
    private final int latitudeMIN = -90;
    private final int longitudeMAX = 180;
    private final int longitudeMIN = -180;

}
