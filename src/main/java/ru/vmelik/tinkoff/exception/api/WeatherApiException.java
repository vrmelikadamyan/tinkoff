package ru.vmelik.tinkoff.exception.api;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class WeatherApiException extends RuntimeException {

    public WeatherApiException(String message) {
        super(message);
    }
}
