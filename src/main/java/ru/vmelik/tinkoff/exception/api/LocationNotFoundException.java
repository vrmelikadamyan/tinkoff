package ru.vmelik.tinkoff.exception.api;

public class LocationNotFoundException extends WeatherApiException {
    private static final String MESSAGE = "Location not found";

    public LocationNotFoundException() {
        super(MESSAGE);
    }
}
