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
import ru.vmelik.tinkoff.model.dto.ErrorResponseDto;
import ru.vmelik.tinkoff.model.dto.ValidationErrorResponseDto;
import ru.vmelik.tinkoff.model.dto.WeatherRequestDto;
import ru.vmelik.tinkoff.model.dto.WeatherResponseDto;
import ru.vmelik.tinkoff.service.WeatherService;

import java.util.UUID;

@Tag(name = "Weather REST API operations")
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @Operation(
            summary = "Add new city weather info",
            responses = {
                    @ApiResponse(responseCode = "201", description = "New city weather info was added", content = @Content(schema = @Schema(implementation = UUID.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponseDto.class)))
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{cityId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UUID addWeatherInfo(
            @PathVariable UUID cityId,
            @RequestBody @Valid WeatherRequestDto request) {
        return weatherService.addWeatherInfo(cityId, request);
    }

    @Operation(
            summary = "Get current city weather info",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Weather info", content = @Content(schema = @Schema(implementation = WeatherResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Requested data not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @GetMapping(value = "/{cityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WeatherResponseDto getCurrentCityWeather(@PathVariable UUID cityId) {
        return weatherService.getCurrentCityWeather(cityId);
    }

    @Operation(
            summary = "Update city weather info",
            responses = {
                    @ApiResponse(responseCode = "200", description = "City weather info was updated", content = @Content(schema = @Schema(implementation = UUID.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Requested data not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @PutMapping(value = "/{cityId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UUID updateCityWeather(@PathVariable UUID cityId,
                                  @RequestBody @Valid WeatherRequestDto request) {
        return weatherService.updateCityWeather(cityId, request);
    }

    @Operation(
            summary = "Delete city weather info",
            responses = @ApiResponse(responseCode = "204", description = "City weather info was deleted")
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{cityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCityWeather(@PathVariable UUID cityId) {
        weatherService.deleteCityWeather(cityId);
    }
}
