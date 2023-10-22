package ru.vmelik.tinkoff.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.vmelik.tinkoff.model.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CityRowMapper implements RowMapper<City> {

    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();
        city.setId(UUID.fromString(rs.getString("id")));
        city.setName(rs.getString("name"));

        return city;
    }
}
