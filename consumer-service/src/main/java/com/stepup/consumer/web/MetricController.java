package com.stepup.consumer.web;

import com.stepup.consumer.service.MetricService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code MetricController} class is a Spring RestController responsible
 * for handling HTTP requests related to metrics.
 */
@RestController
@RequestMapping("api/v1/metric")
@AllArgsConstructor
public class MetricController {

    private final MetricService service;

    /**
     * Retrieves all metrics.
     *
     * @return ResponseEntity with HTTP status 200 (OK) and a collection of metrics as the response body.
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Retrieves a metric by its ID.
     *
     * @param id The ID of the metric to retrieve.
     * @return ResponseEntity with HTTP status 200 (OK) and the requested metric as the response body,
     * or HTTP status 404 (Not Found) if the metric does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
