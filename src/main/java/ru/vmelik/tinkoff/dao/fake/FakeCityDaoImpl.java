package ru.vmelik.tinkoff.dao.fake;

import org.springframework.stereotype.Repository;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.model.entity.City;

import java.util.List;

@Repository
public class FakeCityDaoImpl extends FakeCrudOperations<City> implements CityDao {
    @Override
    public List<City> findAll() {
        return entityMap.values().stream().toList();
    }
}
