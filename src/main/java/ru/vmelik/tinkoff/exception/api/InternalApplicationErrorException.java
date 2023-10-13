package ru.vmelik.tinkoff.exception.api;

public class InternalApplicationErrorException extends WeatherApiException {

    public InternalApplicationErrorException(String message) {
        super(message);
    }
}
