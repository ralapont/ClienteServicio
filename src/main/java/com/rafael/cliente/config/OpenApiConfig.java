package com.rafael.cliente.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cliente API")
                        .version("v1")
                        .description("API para gestión de clientes")
                        .contact(new Contact().name("Rafael").email("tu-email@ejemplo.com"))
                        .license(new License().name("MIT")));
    }
}
