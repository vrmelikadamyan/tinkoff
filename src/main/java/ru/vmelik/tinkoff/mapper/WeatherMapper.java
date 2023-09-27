package ru.vmelik.tinkoff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vmelik.tinkoff.model.City;
import ru.vmelik.tinkoff.model.Weather;
import ru.vmelik.tinkoff.model.dto.WeatherRequestDto;
import ru.vmelik.tinkoff.model.dto.WeatherResponseDto;

import java.util.UUID;

@Mapper
public interface WeatherMapper {

    Weather toWeather(UUID cityId, WeatherRequestDto dto);

    @Mapping(target = "cityName", source = "city.name")
    WeatherResponseDto toResponse(Weather weather, City city);
}