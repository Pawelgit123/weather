package com.sda.weather.localisation;

import com.sda.weather.forecast.ForecastData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cityName;
    @Column(nullable = false)
    private String country;
    private String region;
    @Column(nullable = false)
    private int latitude;
    @Column(nullable = false)
    private int longitude;

    @OneToMany(mappedBy = "localisation", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ForecastData> forecastDataList;

    public Optional<String> getRegion() {
        return Optional.of(region);
    }
}
