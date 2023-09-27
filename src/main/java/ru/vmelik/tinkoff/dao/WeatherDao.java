package ru.vmelik.tinkoff.dao;

import jakarta.annotation.Nullable;
import ru.vmelik.tinkoff.model.Weather;

import java.time.LocalDate;
import java.util.UUID;

public interface WeatherDao extends CrudOperations<Weather, UUID> {

    @Nullable
    Weather findByCityAndDate(UUID cityId, LocalDate dateTime);

    void deleteAllByCity(UUID cityId);
}
