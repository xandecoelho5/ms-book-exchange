package com.xandecoelho5.cambioservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(info = @Info(title = "Cambio Service API", version = "v1", description = "Documentation of Cambio Service API"))
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(info());
    }

    private io.swagger.v3.oas.models.info.Info info() {
        return new io.swagger.v3.oas.models.info.Info()
                .title("Cambio Service API")
                .version("v1")
                .description("Documentation of Cambio Service API")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://springdoc.org")
                );
    }
}
