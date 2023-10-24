package ru.vmelik.tinkoff.dao;

import ru.vmelik.tinkoff.model.entity.City;

import java.util.List;
import java.util.UUID;

public interface CityDao extends CrudOperations<City, UUID> {

    List<City> findAll();
}
