package ru.vmelik.tinkoff.dao.hibernate;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vmelik.tinkoff.dao.WeatherDao;
import ru.vmelik.tinkoff.dao.repository.WeatherRepository;
import ru.vmelik.tinkoff.model.entity.Weather;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class HibernateWeatherDaoImpl implements WeatherDao {
    private final WeatherRepository weatherRepository;

    @Nonnull
    @Override
    public Weather create(@Nonnull Weather entity) {
        return weatherRepository.save(entity);
    }

    @Nullable
    @Override
    public Weather findById(@Nonnull UUID uuid) {
        return weatherRepository.findById(uuid).orElse(null);
    }

    @Nonnull
    @Override
    public Weather update(@Nonnull Weather entity) {
        return weatherRepository.save(entity);
    }

    @Nullable
    @Override
    public Weather delete(@Nonnull UUID uuid) {
        Optional<Weather> weather = weatherRepository.findById(uuid);
        weather.ifPresent(weatherRepository::delete);

        return weather.orElse(null);
    }

    @Nullable
    @Override
    public Weather findByCityAndDate(UUID cityId, LocalDate dateTime) {
        return weatherRepository.findByCity_IdAndDateTime(cityId, dateTime.atStartOfDay()).orElse(null);
    }

    @Override
    public void deleteAllByCity(UUID cityId) {
        weatherRepository.deleteAllByCity_Id(cityId);
    }
}
