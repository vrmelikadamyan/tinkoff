package ru.vmelik.tinkoff.dao.fake;

import org.springframework.stereotype.Repository;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.model.entity.City;

@Repository
public class FakeCityDaoImpl extends FakeCrudOperations<City> implements CityDao {
}
