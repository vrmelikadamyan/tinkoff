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
public class LocationDto {

    private String name;

    private String country;

    @JsonProperty("localtime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime localTime;
}
