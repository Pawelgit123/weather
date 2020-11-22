package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    public Optional<String> getRegion() {
        return Optional.of(region);
    }
}
