package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    @ManyToOne
    Localisation localisation;
    LocalDate date;

}
