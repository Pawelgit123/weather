package com.sda.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int airTemperature;
    int airPressure;
    int airHumidity;
    String windDirection;
    int windSpeed;

}
