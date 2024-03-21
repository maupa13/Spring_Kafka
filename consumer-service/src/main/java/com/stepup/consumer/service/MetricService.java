package com.stepup.consumer.service;

import com.stepup.consumer.model.Metric;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@code MetricService} class is a Spring service component
 * responsible for managing metrics in the Spring_Kafka application.
 */
@Service
@AllArgsConstructor
@Slf4j
public class MetricService {

    private final ConcurrentHashMap<String, Metric> map;

    /**
     * Retrieves all metrics stored in the ConcurrentHashMap.
     *
     * @return A collection of all metrics.
     */
    public Collection<Metric> findAll() {
        return map.values();
    }

    /**
     * Retrieves a metric by its unique identifier.
     *
     * @param id The unique identifier of the metric to retrieve.
     * @return The metric with the specified identifier.
     * @throws DataAccessResourceFailureException if the metric with the specified identifier does not exist.
     */
    public Metric findById(String id) {
        var metric = map.get(id);

        if (Objects.nonNull(metric)) {
            return metric;
        }

        log.warn("Metric with id: '" + id + "' does not exist!");
        throw new DataAccessResourceFailureException("Metric with id: '" + id + "' does not exist!");
    }

    /**
     * Retrieves a metric by its name.
     *
     * @param name The name of the metric to retrieve.
     * @return The metric with the name, or null if not found.
     */
    public Metric findByName(String name) {

        // Iterate through the map entries to find the Metric with the matching name
        for (Map.Entry<String, Metric> entry : map.entrySet()) {
            Metric metric = entry.getValue();
            if (metric.getName().equals(name)) {
                return metric;
            }
        }

        // If no Metric with the specified name is found, return null
        log.warn("Metric with name: '" + name + "' does not exist!");
        throw new DataAccessResourceFailureException("Metric with name: '" + name + "' does not exist!");
    }


    /**
     * Updates an existing metric or inserts a new one if it doesn't exist.
     *
     * @param metric The metric to update or insert.
     */
    public void update(Metric metric) {
        if (map.containsKey(metric.getId())) {
            map.replace(metric.getId(), metric);
            log.info("MetricService.update " + metric + " was updated!");
        } else {
            insert(metric);
        }
    }

    /**
     * Deletes a metric from the ConcurrentHashMap.
     *
     * @param metric The metric to delete.
     */
    public void delete(Metric metric) {
        map.remove(metric.getId());
        log.info("MetricService.delete " + metric + " was deleted!");
    }

    /**
     * Inserts a new metric into the ConcurrentHashMap.
     *
     * @param metric The metric to insert.
     */
    public void insert(Metric metric) {
        map.put(metric.getId(), metric);
        log.info("MetricService.insert " + metric + " was inserted!");
    }
}
