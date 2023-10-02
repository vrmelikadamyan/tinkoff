package ru.vmelik.tinkoff.service;

import ru.vmelik.tinkoff.model.dto.WeatherRequestDto;
import ru.vmelik.tinkoff.model.dto.WeatherResponseDto;

import java.util.UUID;

public interface WeatherService {

    UUID addWeatherInfo(UUID cityId, WeatherRequestDto weatherInfo);

    WeatherResponseDto getCurrentCityWeather(UUID cityId);

    UUID updateCityWeather(UUID cityId, WeatherRequestDto weatherInfo);

    void deleteCityWeather(UUID cityId);
}
