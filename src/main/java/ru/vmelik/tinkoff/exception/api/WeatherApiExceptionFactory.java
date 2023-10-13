package ru.vmelik.tinkoff.exception.api;

public class WeatherApiExceptionFactory {

    public static WeatherApiException createException(int errorCode, String message) {
        return switch (errorCode) {
            case 1006 -> new LocationNotFoundException();
            case 1002, 2006, 2007, 2008, 2009 -> new ApiKeyException(message);
            case 1003, 1005, 9000, 9001 -> new InvalidRequestException(message);
            case 9999 -> new InternalApplicationErrorException(message);
            default -> throw new RuntimeException(message);
        };
    }
}
