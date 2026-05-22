package ru.soft.paymentservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI paymentServiceOpenApi(
            @Value("${server.port:8082}") String serverPort) {
        return new OpenAPI()
                .info(new Info()
                        .title("Payment Service API")
                        .version("1.0")
                        .description("REST API оплат: CRUD и выборка по идентификатору заказа.")
                        .contact(new Contact().name("internet-shop")))
                .servers(List.of(new Server().url("http://localhost:" + serverPort).description("Локальный сервер")));
    }
}
