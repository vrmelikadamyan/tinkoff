package ru.vmelik.tinkoff.dao.jdbc;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.vmelik.tinkoff.dao.WeatherDao;
import ru.vmelik.tinkoff.mapper.WeatherRowMapper;
import ru.vmelik.tinkoff.model.entity.Weather;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class JdbcWeatherDaoImpl implements WeatherDao {
    private static final String INSERT_SQL = "insert into weather(city_id, temperature, date_time, type_id) values (?, ?, ?, ?) ";
    private static final String SELECT_SQL = """
            select w.id          as id,
                   w.temperature as temperature,
                   w.date_time   as date_time,
                   c.id          as city_id,
                   c.name        as city_name,
                   wt.id         as type_id,
                   wt.name       as type_name
            from weather w
                     join city c on w.city_id = c.id
                     join weather_type wt on wt.id = w.type_id
            where w.id = ?\s""";
    private static final String UPDATE_SQL = "update weather set city_id = ?, temperature = ?, date_time = ?, type_id = ? where id = ? ";
    private static final String DELETE_SQL = "delete from weather where id = ? ";

    private final JdbcTemplate jdbcTemplate;

    @Nonnull
    @Override
    public Weather create(@Nonnull Weather entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection
                            .prepareStatement(INSERT_SQL);
                    ps.setString(1, entity.getCity().getId().toString());
                    ps.setInt(2, entity.getTemperature());
                    ps.setString(3, entity.getDateTime().toString());
                    ps.setString(4, entity.getType().getId().toString());
                    return ps;
                }
        );

        entity.setId(UUID.fromString(String.valueOf(keyHolder.getKeyList().get(0).get("id"))));
        return entity;
    }

    @Nullable
    @Override
    public Weather findById(@Nonnull UUID uuid) {
        Weather weather = jdbcTemplate.queryForObject(SELECT_SQL, new WeatherRowMapper(), uuid);

        return weather;
    }

    @Nonnull
    @Override
    public Weather update(@Nonnull Weather entity) {
        jdbcTemplate.update(UPDATE_SQL,
                entity.getCity().getId(),
                entity.getTemperature(),
                entity.getDateTime().toString(),
                entity.getType().getId(),
                entity.getId());

        return entity;
    }

    @Nullable
    @Override
    public Weather delete(@Nonnull UUID uuid) {
        Optional<Weather> weather = Optional.ofNullable(findById(uuid));
        weather.ifPresent(c -> jdbcTemplate.update(DELETE_SQL, c.getId()));

        return weather.orElse(null);
    }

    @Nullable
    @Override
    public Weather findByCityAndDate(UUID cityId, LocalDate dateTime) {
        return null;
    }

    @Override
    public void deleteAllByCity(UUID cityId) {

    }
}
