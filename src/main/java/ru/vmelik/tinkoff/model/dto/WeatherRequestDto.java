package ru.vmelik.tinkoff.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRequestDto {

    @NotNull
    private Integer temperature;

    @NotNull
    private LocalDate dateTime;
}
