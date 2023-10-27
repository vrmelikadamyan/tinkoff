package ru.vmelik.tinkoff.api;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class WeatherApiIntegrationTest {

    @Mock
    private WebClient weatherApiClient;
}
