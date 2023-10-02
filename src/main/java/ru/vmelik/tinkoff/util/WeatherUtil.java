package ru.vmelik.tinkoff.util;

import ru.vmelik.tinkoff.model.CityWeather;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class WeatherUtil {
    private WeatherUtil() {
    }

    public static void printStat(List<CityWeather> weatherData) {
        weatherData
                .stream()
                .collect(
                        Collectors.groupingBy(
                                CityWeather::getCityName,
                                Collectors.mapping(CityWeather::getTemperature, Collectors.toList())))
                .forEach((key, value) -> System.out.printf("Region: %s, temperatures: %s%n", key, value.toString()));
    }

    public static Map<UUID, List<Integer>> getRegionsTemperatureMap(List<CityWeather> weatherData) {
        return weatherData
                .stream()
                .collect(
                        Collectors.groupingBy(
                                CityWeather::getCityId,
                                Collectors.mapping(CityWeather::getTemperature, Collectors.toList())));
    }

    public static Double getAverageTemperatureByRegion(List<CityWeather> weatherData, UUID regionId) {
        return weatherData
                .stream()
                .filter(weather -> weather.getCityId().equals(regionId))
                .mapToInt(CityWeather::getTemperature)
                .average()
                .orElseThrow(() -> new RuntimeException(String.format("Region with id %s not found!", regionId)));
    }

    public static Map<UUID, Double> getRegionsAverageTemperatureMap(List<CityWeather> weatherData) {
        return weatherData
                .stream()
                .collect(Collectors.groupingBy(
                        CityWeather::getCityId,
                        Collectors.averagingInt(CityWeather::getTemperature)
                ));
    }

    public static Set<String> findRegionsAboveThreshold(List<CityWeather> weatherData, int threshold) {
        return weatherData
                .stream()
                .filter(weather -> weather.getTemperature() > threshold)
                .map(CityWeather::getCityName)
                .collect(Collectors.toSet());
    }

    public static Map<Integer, List<CityWeather>> getTemperatureMap(List<CityWeather> weatherData) {
        return weatherData
                .stream()
                .collect(Collectors.groupingBy(
                        CityWeather::getTemperature
                ));
    }
}
