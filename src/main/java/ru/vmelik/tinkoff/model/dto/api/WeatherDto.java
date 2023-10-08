package ru.vmelik.tinkoff.model.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vmelik.tinkoff.converter.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    @JsonProperty("last_updated")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdated;

    @JsonProperty("temp_c")
    private Integer temp;
}
