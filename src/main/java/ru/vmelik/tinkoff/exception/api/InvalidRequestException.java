package ru.vmelik.tinkoff.exception.api;

public class InvalidRequestException extends WeatherApiException {

    public InvalidRequestException(String message) {
        super(message);
    }
}
