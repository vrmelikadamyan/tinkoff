package ru.vmelik.tinkoff.mapper;

import org.mapstruct.Mapper;
import ru.vmelik.tinkoff.model.City;
import ru.vmelik.tinkoff.model.dto.CityRequestDto;

@Mapper
public interface CityMapper {

    City toCity(CityRequestDto cityRequestDto);
}