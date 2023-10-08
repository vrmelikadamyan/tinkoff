package ru.vmelik.tinkoff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import ru.vmelik.tinkoff.config.api.WeatherApiConfig;
import ru.vmelik.tinkoff.exception.api.WeatherApiExceptionFactory;
import ru.vmelik.tinkoff.model.dto.api.WeatherApiErrorDto;

@Configuration
public class WebClientConfig {
    private static final String KEY_PARAM = "key";

    @Bean
    public WebClient weatherApiClient(WeatherApiConfig weatherApiConfig) {
        return WebClient
                .builder()
                .filter(errorHandler())
                .baseUrl(weatherApiConfig.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .uriBuilderFactory(new DefaultUriBuilderFactory(uriComponentsBuilder(weatherApiConfig)))
                .build();
    }

    private UriComponentsBuilder uriComponentsBuilder(WeatherApiConfig weatherApiConfig) {
        return UriComponentsBuilder.fromHttpUrl(weatherApiConfig.getBaseUrl())
                .queryParam(KEY_PARAM, weatherApiConfig.getKey());
    }

    private ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(
                clientResponse -> {
                    if (clientResponse.statusCode().isError()) {
                        return clientResponse
                                .bodyToMono(WeatherApiErrorDto.class)
                                .flatMap(error -> Mono.error(
                                        WeatherApiExceptionFactory.createException(
                                                error.getError().getCode(), error.getError().getMessage())));
                    }

                    return Mono.just(clientResponse);
                }
        );
    }
}
