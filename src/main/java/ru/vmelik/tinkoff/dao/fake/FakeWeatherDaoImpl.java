package ru.vmelik.tinkoff.dao.fake;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Repository;
import ru.vmelik.tinkoff.dao.WeatherDao;
import ru.vmelik.tinkoff.model.entity.Weather;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeWeatherDaoImpl extends FakeCrudOperations<Weather> implements WeatherDao {

    @Nullable
    @Override
    public Weather findByCityAndDate(UUID cityId, LocalDate dateTime) {
        return entityMap.values()
                .stream().filter(weather -> weather.getCity().getId().equals(cityId) && weather.getDateTime().equals(dateTime))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteAllByCity(UUID cityId) {
        List<Weather> foundedElements = entityMap.values()
                .stream().filter(weather -> weather.getCity().getId().equals(cityId))
                .toList();

        foundedElements.forEach(weather -> entityMap.remove(weather.getId()));
    }
}
