package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDTO {

    String cityName;
    String country;
    String latitude;
    String longitude;
}
