package ru.vmelik.tinkoff.model.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiErrorDto {
    private Error error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        private Integer code;

        private String message;
    }
}
