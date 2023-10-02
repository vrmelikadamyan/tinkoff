package ru.vmelik.tinkoff.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vmelik.tinkoff.model.dto.CityRequestDto;
import ru.vmelik.tinkoff.model.dto.ValidationErrorResponseDto;
import ru.vmelik.tinkoff.service.CityService;

import java.util.UUID;

@Tag(name = "City REST API operations")
@RestController
@RequestMapping("/api/weather/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @Operation(
            summary = "Add new city",
            responses = {
                    @ApiResponse(responseCode = "201", description = "New city was added", content = @Content(schema = @Schema(implementation = UUID.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponseDto.class)))
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UUID createCity(@RequestBody @Valid CityRequestDto request) {
        return cityService.create(request);
    }
}
