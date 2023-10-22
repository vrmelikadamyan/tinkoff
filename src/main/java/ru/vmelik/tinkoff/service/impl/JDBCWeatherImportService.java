package ru.vmelik.tinkoff.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.vmelik.tinkoff.api.WeatherApi;
import ru.vmelik.tinkoff.dao.CityDao;
import ru.vmelik.tinkoff.dao.WeatherDao;
import ru.vmelik.tinkoff.model.dto.api.CurrentWeatherResponseDto;
import ru.vmelik.tinkoff.model.entity.City;
import ru.vmelik.tinkoff.model.entity.Weather;
import ru.vmelik.tinkoff.service.WeatherImportService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

@Service
@AllArgsConstructor
public class JDBCWeatherImportService implements WeatherImportService {

    @Qualifier("hibernateWeatherDaoImpl")
    private final WeatherDao weatherDao;
    @Qualifier("hibernateCityDaoImpl")
    private final CityDao cityDao;

    private final WeatherApi weatherApi;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void importWeather() {
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();

            connection.setAutoCommit(false);
            importWeatherToDb();
            connection.commit();

        } catch (SQLException ex) {

        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void importWeatherReadCommitted() {
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            importWeatherToDb();
            connection.commit();

        } catch (SQLException ex) {

        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void importWeatherSerializable() {
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();

            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            importWeatherToDb();
            connection.commit();

        } catch (SQLException ex) {

        } finally {
            closeConnection(connection);
        }
    }

    private void importWeatherToDb() {
        Set<String> cities = Set.of("Paris", "Moscow", "London");

        cities.forEach(city -> cityDao.create(City.builder().name(city).build()));

        cityDao.findAll().forEach(city -> {
                    CurrentWeatherResponseDto response = weatherApi.current(city.getName());
                    Weather weather = Weather
                            .builder()
                            .city(city)
                            .temperature(response.getCurrent().getTemp())
                            .dateTime(response.getLocation().getLocalTime())
                            .build();

                    weatherDao.create(weather);
                }
        );
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {

        }
    }
}
