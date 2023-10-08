package ru.vmelik.tinkoff.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentWeatherResponseDto {

    private LocationDto location;

    private WeatherDto current;
}
