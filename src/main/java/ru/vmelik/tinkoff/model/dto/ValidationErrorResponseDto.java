package ru.vmelik.tinkoff.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ValidationErrorResponseDto
        extends ErrorResponseDto {
    private final List<ErrorDescriptionDto> errors;

    public ValidationErrorResponseDto(String message, List<ErrorDescriptionDto> errors) {
        super(message);
        this.errors = errors;
    }
}