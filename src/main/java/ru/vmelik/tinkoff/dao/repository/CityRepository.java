package ru.vmelik.tinkoff.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vmelik.tinkoff.model.entity.City;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
}
