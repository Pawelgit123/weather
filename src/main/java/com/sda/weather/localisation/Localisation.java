package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private Instant createdate;
    @CreatedBy
    private String createdBy;

    public Localisation(Long id, String cityName, String country, String region, int latitude, int longitude) {
        this.id = id;
        this.cityName = cityName;
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Optional<String> getRegion() {
        return Optional.of(region);
    }
}
