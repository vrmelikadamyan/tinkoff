package ru.vmelik.tinkoff.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.vmelik.tinkoff.exception.NotFoundException;
import ru.vmelik.tinkoff.model.dto.ErrorDescriptionDto;
import ru.vmelik.tinkoff.model.dto.ErrorResponseDto;
import ru.vmelik.tinkoff.model.dto.ValidationErrorResponseDto;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Hidden
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponseDto badRequest(MethodArgumentNotValidException exception) {
        final BindingResult bindingResult = exception.getBindingResult();
        return new ValidationErrorResponseDto(buildMessage(bindingResult), buildErrors(bindingResult));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto error(NotFoundException exception) {
        return new ErrorResponseDto(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponseDto conflict(IllegalStateException exception) {
        return new ErrorResponseDto(exception.getMessage());
    }

    private String buildMessage(BindingResult bindingResult) {
        return String.format("Error on %s, rejected errors [%s]",
                bindingResult.getTarget(),
                bindingResult.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(joining(";")));
    }

    private List<ErrorDescriptionDto> buildErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(e -> new ErrorDescriptionDto(e.getField(), e.getDefaultMessage()))
                .collect(toList());
    }
}
