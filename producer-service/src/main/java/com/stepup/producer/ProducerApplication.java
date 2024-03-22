package com.stepup.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main class for the ProducerApplication.
 * This class is annotated with {@link org.springframework.boot.autoconfigure.SpringBootApplication}
 * to indicate that it is a Spring Boot application and to enable auto-configuration.
 * The {@link org.springframework.boot.autoconfigure.SpringBootApplication#exclude()} attribute
 * is used to exclude the {@link org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration}
 * class, indicating that the application does not require a data source.
 *
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 * @see org.springframework.boot.SpringApplication
 */
@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
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