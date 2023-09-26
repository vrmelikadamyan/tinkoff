package ru.vmelik.tinkoff.weather;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class WeatherTestUtil {
    private static final int MIN_TEMPERATURE = 0;
    private static final int MAX_TEMPERATURE = 30;

    private WeatherTestUtil() {
    }

    public static List<Weather> generateRandomRegionWeatherList(String region, int size) {
        UUID regionId = UUID.randomUUID();

        List<Weather> weatherList = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            weatherList.add(
                    new Weather(regionId, region, randomTemperature(), LocalDateTime.now().minusDays(i)));
        }

        return weatherList;
    }

    private static int randomTemperature() {
        return new Random().nextInt(MIN_TEMPERATURE, MAX_TEMPERATURE + 1);
    }
}
