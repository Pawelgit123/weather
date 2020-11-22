package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component
public class LocalisationParamLimit {

    private final int latitudeMAX = 90;
    private final int latitudeMIN = -90;
    private final int longitudeMAX = 180;
    private final int longitudeMIN = -180;

}
