package com.stepup.consumer.repository;

import com.stepup.consumer.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository interface for managing metrics.
 *
 * @see org.springframework.stereotype.Repository
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see Metric
 */
@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
}
