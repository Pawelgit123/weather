package com.sda.weather;


import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ToString
@ConfigurationProperties("com.sda.weather.apiconfiguration-api")
public class ApiConfiguration {

    private String apikey;
    private String url;
    private String units;
}
