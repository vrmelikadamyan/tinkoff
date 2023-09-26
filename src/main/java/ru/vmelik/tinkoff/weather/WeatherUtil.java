package ru.vmelik.tinkoff.weather;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class WeatherUtil {
    private WeatherUtil() {
    }

    public static void printStat(List<Weather> weatherData) {
        weatherData
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Weather::getRegionName,
                                Collectors.mapping(Weather::getTemperature, Collectors.toList())))
                .forEach((key, value) -> System.out.printf("Region: %s, temperatures: %s%n", key, value.toString()));
    }

    public static Map<UUID, List<Integer>> getRegionsTemperatureMap(List<Weather> weatherData) {
        return weatherData
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Weather::getRegionId,
                                Collectors.mapping(Weather::getTemperature, Collectors.toList())));
    }

    public static Double getAverageTemperatureByRegion(List<Weather> weatherData, UUID regionId) {
        return weatherData
                .stream()
                .filter(weather -> weather.getRegionId().equals(regionId))
                .mapToInt(Weather::getTemperature)
                .average()
                .orElseThrow(() -> new RuntimeException(String.format("Region with id %s not found!", regionId)));
    }

    public static Map<UUID, Double> getRegionsAverageTemperatureMap(List<Weather> weatherData) {
        return weatherData
                .stream()
                .collect(Collectors.groupingBy(
                        Weather::getRegionId,
                        Collectors.averagingInt(Weather::getTemperature)
                ));
    }

    public static Set<String> findRegionsAboveThreshold(List<Weather> weatherData, int threshold) {
        return weatherData
                .stream()
                .filter(weather -> weather.getTemperature() > threshold)
                .map(Weather::getRegionName)
                .collect(Collectors.toSet());
    }

    public static Map<Integer, List<Weather>> getTemperatureMap(List<Weather> weatherData) {
        return weatherData
                .stream()
                .collect(Collectors.groupingBy(
                        Weather::getTemperature
                ));
    }
}
