package ru.vmelik.tinkoff.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vmelik.tinkoff.model.Identity;

import java.util.UUID;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Identity<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
}
