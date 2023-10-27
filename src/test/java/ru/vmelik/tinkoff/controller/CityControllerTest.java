package ru.vmelik.tinkoff.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.vmelik.tinkoff.model.dto.CityRequestDto;
import ru.vmelik.tinkoff.service.impl.CityServiceImpl;

import java.util.UUID;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = CityController.class)
public class CityControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CityServiceImpl cityService;

    @Test
    void successCreateCity() {
        // given
        UUID id = UUID.randomUUID();
        CityRequestDto request = new CityRequestDto("Paris");

        // when
        when(cityService.create(Mockito.any())).thenReturn(id);

        // then
        webTestClient.post()
                .uri("/api/weather/city")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class)
                .isEqualTo(String.format("\"%s\"", id));
    }

    @Test
    void failCreateCityWithEmptyName() {
        webTestClient.post()
                .uri("/api/weather/city")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new CityRequestDto(""))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void failCreateCityWithNullName() {
        webTestClient.post()
                .uri("/api/weather/city")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new CityRequestDto(null))
                .exchange()
                .expectStatus().isBadRequest();
    }
}
