package com.sda.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ToString
@Component
@Data
@ConfigurationProperties(prefix = "configuration")
public class Configuration {

//    private String apikey = "=&appid=a5bd02ecf7c1f72449ae4d087d08d275";
//    private String urlCurrent = "=http://api.openweathermap.org/data/2.5/weather?q=";
//    private String urlForecast = "=https://api.openweathermap.org/data/2.5/onecall?";
//    private String units = "=&units=metric";

    private String apikey;
//    private String urlCurrent;
//    private String urlForecast;
//    private String units;

    @Bean
    ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
