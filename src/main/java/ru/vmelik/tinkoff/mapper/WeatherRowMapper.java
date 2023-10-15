package ru.vmelik.tinkoff.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.vmelik.tinkoff.model.entity.City;
import ru.vmelik.tinkoff.model.entity.Weather;
import ru.vmelik.tinkoff.model.entity.WeatherType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class WeatherRowMapper implements RowMapper<Weather> {
    @Override
    public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {
        Weather weather = new Weather();

        weather.setId(UUID.fromString(rs.getString("id")));
        weather.setTemperature(rs.getInt("temperature"));
        weather.setDateTime(LocalDateTime.parse(rs.getString("date_time")));

        City city = new City();
        city.setId(UUID.fromString(rs.getString("city_id")));
        city.setName(rs.getString("city_name"));
        weather.setCity(city);

        WeatherType weatherType = new WeatherType();
        weatherType.setId(UUID.fromString(rs.getString("type_id")));
        weatherType.setName(rs.getString("type_name"));
        weather.setType(weatherType);

        return weather;
    }
}
