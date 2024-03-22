package com.stepup.producer.web;

import com.stepup.producer.kafka.MetricProducer;
import com.stepup.producer.model.MetricDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
     * Handles HTTP POST requests to insert a new metricDto.
     *
     * @param metricDto The metricDto to insert.
     * @return ResponseEntity with HTTP status 200 (OK) if the insertion is successful.
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody MetricDto metricDto) {
        service.insert(metricDto);
        return ResponseEntity.ok().build();
    }
}
