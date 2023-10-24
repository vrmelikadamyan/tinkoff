package ru.vmelik.tinkoff.dao.hibernate;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.dao.repository.CityRepository;
import ru.vmelik.tinkoff.model.entity.City;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class HibernateCityDaoImpl implements CityDao {
    private final CityRepository cityRepository;

    @Nonnull
    @Override
    public City create(@Nonnull City entity) {
        return cityRepository.save(entity);
    }

    @Nullable
    @Override
    public City findById(@Nonnull UUID uuid) {
        return cityRepository.findById(uuid).orElse(null);
    }

    @Nonnull
    @Override
    public City update(@Nonnull City entity) {
        return cityRepository.save(entity);
    }

    @Nullable
    @Override
    public City delete(@Nonnull UUID uuid) {
        Optional<City> city = cityRepository.findById(uuid);
        city.ifPresent(cityRepository::delete);

        return city.orElse(null);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
