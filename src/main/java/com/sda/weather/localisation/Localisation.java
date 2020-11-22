package com.sda.weather.localisation;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String cityName;
    @NotNull
    private String country;
    @NotNull
    private String region;
    @NotNull
    private int latitude;
    @NotNull
    private int longitude;

    public Optional<String> getRegion() {
        return Optional.of(region);
    }
}
