package com.stepup.consumer.service;

import com.stepup.consumer.entity.Metric;
import com.stepup.consumer.exception.MetricServiceException;
import com.stepup.consumer.model.MetricDto;
import com.stepup.consumer.repository.MetricRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The {@code MetricService} class is a Spring service component
 * responsible for managing metrics in the Spring_Kafka application.
 */
@Service
@AllArgsConstructor
@Slf4j
public class MetricService {

    private final MetricRepository metricRepository;

    /**
     * Retrieves all metrics stored in the MetricRepository.
     *
     * @return A collection of all metrics.
     */
    public Collection<MetricDto> findAll() {
        try {
            List<Metric> metricList = metricRepository.findAll();
            return mapToMetricDtoList(metricList);
        } catch (Exception e) {
            log.error("Failed to get all metrics: " + e.getMessage());
            throw new MetricServiceException("Failed to get all metrics: "
                                             + e.getMessage());
        }
    }

    /**
     * Retrieves a metric by its unique identifier.
     *
     * @param id The unique identifier of the metric to retrieve.
     * @return The metric with the specified identifier.
     * @throws DataAccessResourceFailureException if the metric with the specified identifier does not exist.
     */
    public Metric findById(Long id) {
        try {
            Optional<Metric> optionalMetric = metricRepository.findById(id);
            return optionalMetric.orElse(null);
        } catch (Exception e) {
            log.error("Failed to get metrics by ID: " + e.getMessage());
            throw new MetricServiceException("Failed to get metrics by ID: "
                                             + e.getMessage());
        }
    }

    /**
     * Inserts a new metricDto into the MetricRepository.
     *
     * @param metricDto The metricDto to insert.
     */
    public void insert(MetricDto metricDto) {
        try {
            Metric metric = new Metric();
            metric.setName(metricDto.getName());
            metric.setValue(metricDto.getValue());
            metric.setUpdated(metricDto.getUpdated());

            metricRepository.save(metric);
            log.info("Created metric: {}", metricDto);
        } catch (Exception e) {
            log.error("Failed to create metric: " + e.getMessage());
            throw new MetricServiceException("Failed to create metric: "
                                             + e.getMessage());
        }
    }

    /**
     * Utility method to map Metric entities to MetricDto objects.
     *
     * @param metricList the list of Metric entities
     * @return a list of MetricDto objects mapped from the Metric entities
     */
    private List<MetricDto> mapToMetricDtoList(List<Metric> metricList) {
        return metricList.stream()
                .map(this::mapToMetricDto)
                .collect(Collectors.toList());
    }

    /**
     * Utility method to map a Metric entity to a MetricDto object.
     *
     * @param metric the Metric entity
     * @return the corresponding MetricDto object
     */
    private MetricDto mapToMetricDto(Metric metric) {
        MetricDto metricDto = new MetricDto();
        metricDto.setId(metric.getId());
        metricDto.setName(metric.getName());
        metricDto.setValue(metric.getValue());
        metricDto.setUpdated(metric.getUpdated());
        return metricDto;
    }
}
