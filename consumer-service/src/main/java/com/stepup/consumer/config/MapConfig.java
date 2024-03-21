package com.stepup.consumer.config;

import com.stepup.consumer.model.Metric;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@code MapConfig} class is a Spring configuration class responsible
 * for ConcurrentHashMap in the application.
 */
@Configuration
public class MapConfig {

    /**
     * Provides a bean definition for a ConcurrentHashMap that stores metrics.
     * This ConcurrentHashMap is thread-safe and suitable for storing metric data
     * in a concurrent environment.
     *
     * @return A new instance of ConcurrentHashMap with String keys and Metric values.
     */
    @Bean
    public ConcurrentHashMap<String, Metric> map() {
        return new ConcurrentHashMap<>();
    }
}
