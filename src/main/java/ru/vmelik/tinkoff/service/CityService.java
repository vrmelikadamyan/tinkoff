package ru.vmelik.tinkoff.service;

import ru.vmelik.tinkoff.model.dto.CityRequestDto;

import java.util.UUID;

public interface CityService {

    UUID create(CityRequestDto city);
}
