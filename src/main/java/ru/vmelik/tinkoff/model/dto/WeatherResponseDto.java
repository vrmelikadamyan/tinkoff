package ru.vmelik.tinkoff.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseDto {

    private UUID cityId;

    private String cityName;

    private int temperature;

    private LocalDate dateTime;
}
