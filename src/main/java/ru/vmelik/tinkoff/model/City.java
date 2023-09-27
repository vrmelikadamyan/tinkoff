package ru.vmelik.tinkoff.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class City implements Identity<UUID> {

    private UUID id;

    private String name;
}
