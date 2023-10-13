package ru.vmelik.tinkoff.api;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.vmelik.tinkoff.config.api.WeatherApiConfig;
import ru.vmelik.tinkoff.model.dto.api.CurrentWeatherResponseDto;

@RateLimiter(name = "weatherapi")
@Service
@RequiredArgsConstructor
public class WeatherApiImpl implements WeatherApi {

    @Qualifier("weatherApiClient")
    private final WebClient weatherApiClient;

    private final WeatherApiConfig weatherApiConfig;

    @Override
    public CurrentWeatherResponseDto current(String city) {

        return weatherApiClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(weatherApiConfig.getEndpoints().getCurrent())
                                .queryParam("q", city)
                                .build())
                .retrieve()
                .bodyToMono(CurrentWeatherResponseDto.class)
                .block();
    }
}
