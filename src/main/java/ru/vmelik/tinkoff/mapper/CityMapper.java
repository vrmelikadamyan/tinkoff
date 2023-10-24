package ru.vmelik.tinkoff.mapper;

import org.mapstruct.Mapper;
import ru.vmelik.tinkoff.model.dto.CityRequestDto;
import ru.vmelik.tinkoff.model.entity.City;

@Mapper
public interface CityMapper {

    City toCity(CityRequestDto cityRequestDto);
}
