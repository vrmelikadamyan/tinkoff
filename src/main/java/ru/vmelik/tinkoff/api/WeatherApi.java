package ru.vmelik.tinkoff.api;

import ru.vmelik.tinkoff.model.dto.api.CurrentWeatherResponseDto;

public interface WeatherApi {

    CurrentWeatherResponseDto current(String city);
}
