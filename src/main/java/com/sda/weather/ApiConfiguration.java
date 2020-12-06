package com.sda.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ToString
@Data
@ConfigurationProperties(prefix = "apiconfiguration")
public class ApiConfiguration {

    private String apikey;

}
