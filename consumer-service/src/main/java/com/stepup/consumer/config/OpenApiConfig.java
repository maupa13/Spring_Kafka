package com.stepup.consumer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The {@code OpenApiConfig} class is a Spring configuration class responsible for defining
 * bean configurations related to OpenAPI documentation in the application.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Provides a bean definition for customizing the OpenAPI documentation for the consumer service.
     *
     * @return A customized instance of OpenAPI representing the consumer service API documentation.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Consumer")
                        .version("1.0")
                        .description("Consume messages")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("maupa13")));
    }
}
