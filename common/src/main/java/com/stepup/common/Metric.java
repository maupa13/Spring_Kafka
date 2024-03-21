package com.stepup.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

/**
 * The {@code Metric} class represents a metric entity in the StepUp application.
 * Metrics are used to measure various aspects of the application's performance, usage, or other relevant data.
 * This class provides getter and setter methods for accessing and modifying metric properties, builder pattern,
 * a toString() method and constructors for creating instances.
 * Metrics are identified by their {@code id} and contain a {@code name} and the {@code Instant} when they were last updated.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Metric implements Serializable {

    /**
     * The unique identifier of the metric.
     */
    private String id;

    /**
     * The name of the metric.
     */
    private String name;

    /**
     * The {@code Instant} when the metric was last updated.
     */
    private Instant updated;
}
