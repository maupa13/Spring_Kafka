package com.stepup.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The {@code ProducerApplication} class serves as the entry point for the Spring_Kafka producer service application.
 * It is annotated with {@code @SpringBootApplication}, indicating that it is a Spring Boot application.
 */
@EnableScheduling
@SpringBootApplication
public class ProducerApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}