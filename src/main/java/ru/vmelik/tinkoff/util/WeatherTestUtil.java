package ru.vmelik.tinkoff.util;

import ru.vmelik.tinkoff.model.CityWeather;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class WeatherTestUtil {
    private static final int MIN_TEMPERATURE = 0;
    private static final int MAX_TEMPERATURE = 30;

    private WeatherTestUtil() {
    }

    public static List<CityWeather> generateRandomRegionWeatherList(String region, int size) {
        UUID regionId = UUID.randomUUID();

        List<CityWeather> weatherList = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            weatherList.add(
                    new CityWeather(regionId, region, randomTemperature(), LocalDate.now().minusDays(i)));
        }

        return weatherList;
    }

    private static int randomTemperature() {
        return new Random().nextInt(MIN_TEMPERATURE, MAX_TEMPERATURE + 1);
    }
}
