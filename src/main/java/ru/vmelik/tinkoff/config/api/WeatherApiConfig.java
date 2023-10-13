package ru.vmelik.tinkoff.config.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "weather-api")
public class WeatherApiConfig {
    private String baseUrl;
    private String key;
    private Endpoints endpoints;

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Endpoints {
        private String current;
    }
}
