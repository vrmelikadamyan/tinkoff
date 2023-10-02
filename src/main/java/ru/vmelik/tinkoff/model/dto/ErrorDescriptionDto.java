package ru.vmelik.tinkoff.model.dto;

import lombok.Data;

@Data
public class ErrorDescriptionDto {
    private final String field;
    private final String error;
}