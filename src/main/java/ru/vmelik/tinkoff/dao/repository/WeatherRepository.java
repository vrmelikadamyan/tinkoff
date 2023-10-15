package ru.vmelik.tinkoff.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vmelik.tinkoff.model.entity.Weather;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface WeatherRepository extends JpaRepository<Weather, UUID> {

    Optional<Weather> findByCity_IdAndDateTime(UUID cityId, LocalDateTime dateTime);

    void deleteAllByCity_Id(UUID cityId);
}
