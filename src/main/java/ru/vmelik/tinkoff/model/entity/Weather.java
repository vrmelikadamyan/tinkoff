package ru.vmelik.tinkoff.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vmelik.tinkoff.model.Identity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "weather")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather implements Identity<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    private int temperature;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private WeatherType type;
}
