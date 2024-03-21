package com.stepup.producer.web;

import com.stepup.producer.kafka.MetricProducer;
import com.stepup.producer.model.Metric;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code MetricController} class is a Spring RestController
 * responsible for handling HTTP requests related to metrics.
 */
@RestController
@RequestMapping("api/v1/metric")
@AllArgsConstructor
public class MetricController {

    private final MetricProducer service;

    /**
     * Handles HTTP DELETE requests to delete a metric by its ID.
     *
     * @param id The ID of the metric to delete.
     * @return ResponseEntity with HTTP status 200 (OK) if the deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Handles HTTP POST requests to insert a new metric.
     *
     * @param metric The metric to insert.
     * @return ResponseEntity with HTTP status 200 (OK) if the insertion is successful.
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Metric metric) {
        service.insert(metric);
        return ResponseEntity.ok().build();
    }

    /**
     * Handles HTTP PUT requests to update an existing metric.
     *
     * @param metric The metric to update.
     * @return ResponseEntity with HTTP status 200 (OK) if the update is successful.
     */
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Metric metric) {
        service.update(metric);
        return ResponseEntity.ok().build();
    }

}
