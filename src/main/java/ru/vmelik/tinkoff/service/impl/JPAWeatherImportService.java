package ru.vmelik.tinkoff.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.vmelik.tinkoff.api.WeatherApi;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.dao.WeatherDao;
import ru.vmelik.tinkoff.model.dto.api.CurrentWeatherResponseDto;
import ru.vmelik.tinkoff.model.entity.City;
import ru.vmelik.tinkoff.model.entity.Weather;
import ru.vmelik.tinkoff.service.WeatherImportService;

import java.util.Set;

@Service
@AllArgsConstructor
public class JPAWeatherImportService implements WeatherImportService {
    @Qualifier("hibernateWeatherDaoImpl")
    private final WeatherDao weatherDao;
    @Qualifier("hibernateCityDaoImpl")
    private final CityDao cityDao;

    private final WeatherApi weatherApi;

    @Override
    @Transactional
    public void importWeather() {
        Set<String> cities = Set.of("Paris", "Moscow", "London");

        cities.forEach(city -> cityDao.create(City.builder().name(city).build()));

        cityDao.findAll().forEach(city -> {
                    CurrentWeatherResponseDto response = weatherApi.current(city.getName());
                    Weather weather = Weather
                            .builder()
                            .city(city)
                            .temperature(response.getCurrent().getTemp())
                            .dateTime(response.getLocation().getLocalTime())
                            .build();

                    weatherDao.create(weather);
                }
        );
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void importWeatherReadCommitted() {
        importWeather();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void importWeatherSerializable() {
        importWeather();
    }
}
