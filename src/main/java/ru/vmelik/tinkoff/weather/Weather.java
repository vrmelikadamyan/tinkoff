package ru.vmelik.tinkoff.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Weather {
    private UUID regionId;

    private String regionName;

    private int temperature;

    private LocalDateTime dateTime;
}
