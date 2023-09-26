package ru.vmelik.tinkoff;

import ru.vmelik.tinkoff.weather.Weather;
import ru.vmelik.tinkoff.weather.WeatherTestUtil;
import ru.vmelik.tinkoff.weather.WeatherUtil;

import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Weather> weatherList =
                Stream.of(
                        WeatherTestUtil.generateRandomRegionWeatherList("Moscow", 10),
                        WeatherTestUtil.generateRandomRegionWeatherList("Tver", 10),
                        WeatherTestUtil.generateRandomRegionWeatherList("Rostov", 10),
                        WeatherTestUtil.generateRandomRegionWeatherList("Saratov", 10),
                        WeatherTestUtil.generateRandomRegionWeatherList("Omsk", 10),
                        WeatherTestUtil.generateRandomRegionWeatherList("Saint-Petersburg", 10)
                ).flatMap(List::stream).toList();

        WeatherUtil.printStat(weatherList);

        System.out.printf("1. Average temperature in regions: %s\n", WeatherUtil.getRegionsAverageTemperatureMap(weatherList));
        System.out.printf("2. Regions with temperature more than 27: %s\n", WeatherUtil.findRegionsAboveThreshold(weatherList, 27));
        System.out.printf("3. Regions weather map: %s\n", WeatherUtil.getRegionsTemperatureMap(weatherList));
        System.out.printf("4. Temperature map: %s\n", WeatherUtil.getTemperatureMap(weatherList));
    }
}
