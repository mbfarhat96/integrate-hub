package com.mohsin.integratehub.controller;

import com.mohsin.integratehub.dto.WeatherResponse;
import com.mohsin.integratehub.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherService weather;

    @Test
    void testWeatherEndpoint() throws Exception {
        WeatherResponse response = new WeatherResponse(
                "Chicago", "US", 20.5, 19.5, 50, "Clear sky", java.time.LocalDateTime.now()
        );

        when(weather.getWeatherForCity("Chicago")).thenReturn(response);

        mvc.perform(get("/api/weather/Chicago"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city", is("Chicago")));
    }
}
