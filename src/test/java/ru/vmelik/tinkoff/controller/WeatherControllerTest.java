package ru.vmelik.tinkoff.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.vmelik.tinkoff.model.dto.WeatherRequestDto;
import ru.vmelik.tinkoff.service.impl.WeatherServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private WeatherServiceImpl weatherService;

    @Test
    void successAddWeatherInfo() {
        // given
        UUID cityId = UUID.randomUUID();
        WeatherRequestDto request = new WeatherRequestDto(15, LocalDate.of(2023, 10, 25));
        UUID weatherId = UUID.randomUUID();

        // when
        when(weatherService.addWeatherInfo(cityId, request)).thenReturn(weatherId);

        // then
        webTestClient.post()
                .uri(String.format("/api/weather/%s", cityId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class)
                .isEqualTo(String.format("\"%s\"", weatherId));
    }

    @Test
    void failAddWeatherInfoWithNullFields() {
        
    }
}
