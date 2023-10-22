package ru.vmelik.tinkoff.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.dao.WeatherDao;
import ru.vmelik.tinkoff.exception.NotFoundException;
import ru.vmelik.tinkoff.mapper.WeatherMapper;
import ru.vmelik.tinkoff.model.dto.WeatherRequestDto;
import ru.vmelik.tinkoff.model.dto.WeatherResponseDto;
import ru.vmelik.tinkoff.model.entity.City;
import ru.vmelik.tinkoff.service.WeatherService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    @Qualifier("hibernateWeatherDaoImpl")
    private final WeatherDao weatherDao;
    @Qualifier("hibernateCityDaoImpl")
    private final CityDao cityDao;

    private final WeatherMapper weatherMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UUID addWeatherInfo(UUID cityId, WeatherRequestDto weatherInfo) {
        City city = findCity(cityId);

        return weatherDao.create(weatherMapper.toWeather(city.getId(), weatherInfo)).getId();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public WeatherResponseDto getCurrentCityWeather(UUID cityId) {
        City city = findCity(cityId);

        return Optional.ofNullable(weatherDao.findByCityAndDate(city.getId(), LocalDate.now()))
                .map(weather -> weatherMapper.toResponse(weather, city))
                .orElseThrow(() -> new NotFoundException(String.format("Weather for the city with id %s was not found!", cityId)));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UUID updateCityWeather(UUID cityId, WeatherRequestDto newWeather) {
        return Optional.ofNullable(weatherDao.findByCityAndDate(cityId, newWeather.getDateTime()))
                .map(existingWeather -> {
                    existingWeather.setTemperature(newWeather.getTemperature());
                    weatherDao.update(existingWeather);
                    return existingWeather.getId();
                })
                .orElse(addWeatherInfo(cityId, newWeather));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteCityWeather(UUID cityId) {
        weatherDao.deleteAllByCity(cityId);
    }

    private City findCity(UUID cityId) {
        return Optional.ofNullable(cityDao.findById(cityId))
                .orElseThrow(() -> new NotFoundException(String.format("City with id %s was not found!", cityId)));
    }
}
