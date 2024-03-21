package com.stepup.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

/**
 * The {@code Metric} class represents a metric model in the Spring_Kafka application.
 * Metrics are identified by their {@code id} and contain a {@code value}
 * and contain a {@code name} and the {@code Instant} when they were last updated.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Metric implements Serializable {

    /**
     * The id of the metric.
     */
    private String id;

    /**
     * The name of the metric.
     */
    private String name;

    /**
     * The value of the metric.
     */
    private String value;

    /**
     * The {@code Instant} when the metric was last updated.
     */
    private Instant updated;
}