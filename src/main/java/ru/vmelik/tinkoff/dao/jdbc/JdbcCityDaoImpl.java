package ru.vmelik.tinkoff.dao.jdbc;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.mapper.CityRowMapper;
import ru.vmelik.tinkoff.model.entity.City;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class JdbcCityDaoImpl implements CityDao {
    private static final String INSERT_SQL = "insert into city(name) values (?) ";
    private static final String SELECT_SQL = "select * from city where id = ? ";
    private static final String SELECT_ALL_SQL = "select * from city";
    private static final String UPDATE_SQL = "update city set name = ? where id = ? ";
    private static final String DELETE_SQL = "delete from city where id = ? ";

    private final JdbcTemplate jdbcTemplate;

    @Nonnull
    @Override
    public City create(@Nonnull City entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection
                            .prepareStatement(INSERT_SQL);
                    ps.setString(1, entity.getName());
                    return ps;
                }
        );

        entity.setId(UUID.fromString(String.valueOf(keyHolder.getKeyList().get(0).get("id"))));
        return entity;
    }

    @Nullable
    @Override
    public City findById(@Nonnull UUID uuid) {
        City city = jdbcTemplate.queryForObject(SELECT_SQL, new CityRowMapper(), uuid);

        return city;
    }

    @Nonnull
    @Override
    public City update(@Nonnull City entity) {
        jdbcTemplate.update(UPDATE_SQL, entity.getName(), entity.getId());

        return entity;
    }

    @Nullable
    @Override
    public City delete(@Nonnull UUID uuid) {
        Optional<City> city = Optional.ofNullable(findById(uuid));
        city.ifPresent(c -> jdbcTemplate.update(DELETE_SQL, c.getId()));

        return city.orElse(null);
    }

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new CityRowMapper());
    }
}
