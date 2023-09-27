package ru.vmelik.tinkoff.dao.fake;

import org.springframework.stereotype.Repository;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.model.City;

@Repository
public class FakeCityDaoImpl extends FakeCrudOperations<City> implements CityDao {
}
