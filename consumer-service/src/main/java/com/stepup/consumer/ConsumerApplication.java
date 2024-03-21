package com.stepup.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@code ConsumerApplication} class serves as the entry point for the Spring_Kafka consumer service application.
 * It is annotated with {@code @SpringBootApplication}, indicating that it is a Spring Boot application.
 */
@SpringBootApplication
public class ConsumerApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}