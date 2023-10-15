package ru.vmelik.tinkoff.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.mapper.CityMapper;
import ru.vmelik.tinkoff.model.dto.CityRequestDto;
import ru.vmelik.tinkoff.service.CityService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    @Qualifier("hibernateCityDaoImpl")
    private final CityDao cityDao;

    private final CityMapper cityMapper;

    @Override
    public UUID create(CityRequestDto city) {
        return cityDao.create(cityMapper.toCity(city)).getId();
    }
}
