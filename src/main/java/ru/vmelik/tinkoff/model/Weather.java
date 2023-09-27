package ru.vmelik.tinkoff.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Weather implements Identity<UUID> {

    private UUID id;

    private UUID cityId;

    private int temperature;

    private LocalDate dateTime;
}
