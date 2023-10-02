package ru.vmelik.tinkoff.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CityWeather {
    private UUID cityId;

    private String cityName;

    private int temperature;

    private LocalDate dateTime;
}
