package com.mohsin.integratehub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI integrateHubOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IntegrateHub API")
                        .description("Unified API for external service integrations (weather, GitHub, currency, stocks, news)")
                        .version("v1.0.0")
                );
    }

}
