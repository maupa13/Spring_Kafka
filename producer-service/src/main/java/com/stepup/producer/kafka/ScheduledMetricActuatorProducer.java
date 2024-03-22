package com.stepup.producer.kafka;

import com.stepup.producer.model.MetricDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A scheduled producer component responsible for fetching metrics
 * from actuator and inserting them into a metric producer.
 */
@Component
@AllArgsConstructor
@Slf4j
public class ScheduledMetricActuatorProducer {

    private final MetricsEndpoint metricsEndpoint;
    private final MetricProducer metricProducer;

    /**
     * Retrieves metrics from the MetricsEndpoint at a fixed rate and inserts them into the metric producer.
     * Metrics are fetched every five minutes.
     */
    @Scheduled(fixedRate = 300000) // Every five minutes
    public void getMetrics() {
        Set<String> metricNames = metricsEndpoint.listNames().getNames();
        Map<String, Object> metricsMap = new HashMap<>();

        for (String name : metricNames) {
            Object value = getValueFromMetric(name);
            metricsMap.put(name, value);
        }

        insertMetrics(metricsMap);
    }

    /**
     * Retrieves the value of a metric from the MetricsEndpoint based on its name.
     *
     * @param metricName The name of the metric.
     * @return The value of the metric, or null if the metric descriptor is not found or has no measurements.
     */
    private Object getValueFromMetric(String metricName) {
        MetricsEndpoint.MetricDescriptor metricDescriptor = metricsEndpoint.metric(metricName, null);
        if (metricDescriptor == null) {
            log.warn("MetricDto descriptor for {} is null", metricName);
            return null;
        }

        List<MetricsEndpoint.Sample> measurements = metricDescriptor.getMeasurements();
        if (measurements.isEmpty()) {
            log.warn("No measurements available for metric {}", metricName);
            return null;
        }

        // If there are multiple measurements, choose first
        return measurements.get(0).getValue();
    }

    /**
     * Inserts metrics into the metric producer.
     *
     * @param metricMap A map containing metric names as keys and their corresponding values.
     */
    private void insertMetrics(Map<String, Object> metricMap) {
        metricMap.forEach((name, value) -> {
            if (value != null) {
                log.info("MetricDto name: {}, Value: {}", name, value);
                MetricDto metricDto = new MetricDto();
                metricDto.setName(name);
                metricDto.setValue(value.toString());
                metricDto.setUpdated(Instant.now());
                insertMetricInProducer(metricDto);
            }
        });
    }

    /**
     * Inserts a metricDto into the metricDto producer.
     *
     * @param metricDto The metricDto to insert.
     */
    private void insertMetricInProducer(MetricDto metricDto) {
        try {
            metricProducer.insert(metricDto);
        } catch (Exception e) {
            log.error("Error inserting metricDto {}: {}", metricDto.getName(), e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
