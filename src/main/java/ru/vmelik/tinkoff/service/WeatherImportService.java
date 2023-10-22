package ru.vmelik.tinkoff.service;

public interface WeatherImportService {

    void importWeather();

    void importWeatherReadCommitted();

    void importWeatherSerializable();
}
