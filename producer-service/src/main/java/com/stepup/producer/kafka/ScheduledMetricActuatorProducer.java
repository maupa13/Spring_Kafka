package com.stepup.producer.kafka;

import com.stepup.producer.model.Metric;
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
import java.util.UUID;

/**
 * A scheduled producer component responsible for fetching metrics from the MetricsEndpoint
 * and inserting them into a metric producer.
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
            log.warn("Metric descriptor for {} is null", metricName);
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
                log.info("Metric name: {}, Value: {}", name, value);
                Metric metric = new Metric();
                metric.setId(UUID.randomUUID().toString());
                metric.setName(name);
                metric.setValue(value.toString());
                metric.setUpdated(Instant.now());
                insertMetricInProducer(metric);
            }
        });
    }

    /**
     * Inserts a metric into the metric producer.
     *
     * @param metric The metric to insert.
     */
    private void insertMetricInProducer(Metric metric) {
        try {
            metricProducer.insert(metric);
        } catch (Exception e) {
            log.error("Error inserting metric {}: {}", metric.getName(), e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
