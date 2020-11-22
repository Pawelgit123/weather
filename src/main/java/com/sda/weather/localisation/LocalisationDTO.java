package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDTO {

    Long id;
    String cityName;
    String country;
    String region;
    int latitude;
    int longitude;
}
