package com.electricity.billingsystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {@Server(description = "Default URL",url = "/e-billing-service/v1")
        }
)
public class SwaggerUIConfig {

    private static final String SCHEME_NAME = "bearerAuth";
    private static final String SCHEME = "bearer";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .components(new Components()
                        .addSecuritySchemes(SCHEME_NAME, createSecurityScheme()))
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));
    }

    private io.swagger.v3.oas.models.info.Info apiInfo() {
        return new io.swagger.v3.oas.models.info.Info()
                .title("E BILLING SYSTEM")
                .version("v1");
    }

    private io.swagger.v3.oas.models.security.SecurityScheme createSecurityScheme() {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .name(SCHEME_NAME)
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }
}