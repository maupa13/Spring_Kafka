package com.stepup.consumer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * Entity class representing a Metric.
 * This class is annotated with {@link jakarta.persistence.Entity} to indicate that it is a JPA entity,
 * {@link lombok.Getter} and {@link lombok.Setter} for generating getter and setter methods,
 * and {@link jakarta.persistence.Table} to specify the name of the database table.
 *
 * @see jakarta.persistence.Entity
 * @see lombok.Getter
 * @see lombok.Setter
 * @see jakarta.persistence.Table
 * @see jakarta.persistence.Id
 * @see jakarta.persistence.GeneratedValue
 * @see jakarta.validation.constraints.NotBlank
 * @see Metric
 */
@Entity
@Getter
@Setter
@Table(name = "metrics")
@AllArgsConstructor
@NoArgsConstructor
public class Metric {

    /**
     * The unique identifier for the metric.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the metric.
     */
    @NotBlank(message = "Name is mandatory")
    private String name;

    /**
     * The value of the metric.
     */
    @NotBlank(message = "Value is mandatory")
    private String value;

    /**
     * The {@code Instant} when the metric was last updated.
     */
    @NotBlank(message = "Time is mandatory")
    private Instant updated;
}
