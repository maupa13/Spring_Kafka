package com.stepup.producer.kafka;

import com.stepup.common.KafkaHeaders;
import com.stepup.common.KafkaTopics;
import com.stepup.producer.model.Metric;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * The {@code MetricProducer} class is a Spring service responsible for producing Kafka messages
 * related to metrics in the application.
 */
@Service
@AllArgsConstructor
@Slf4j
public class MetricProducer {

    private final KafkaTemplate<String, Metric> kafka;

    /**
     * Sends a delete operation message for the metric with the given ID to Kafka.
     *
     * @param id The ID of the metric to delete.
     */
    public void delete(String id) {
        var metric = Metric.builder().id(id).build();
        var record = new ProducerRecord<>(KafkaTopics.METRICS_TOPIC, metric.getId(), metric);
        record.headers().add(KafkaHeaders.CONSUMER_OPERATION, "delete".getBytes());
        var future = kafka.send(record);

        CompletableFuture<SendResult<String, Metric>> completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return future.get();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

        completableFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                log.warn("MetricService.delete - message failed! id: " + id);
            } else {
                log.info("MetricService.delete - message success! id: " + id);
            }
        });
    }

    /**
     * Sends an insert operation message for the given metric to Kafka.
     *
     * @param metric The metric to insert.
     */
    public void insert(Metric metric) {
        var record = new ProducerRecord<>(KafkaTopics.METRICS_TOPIC, metric.getId(), metric);
        record.headers().add(KafkaHeaders.CONSUMER_OPERATION, "insert".getBytes());
        var future = kafka.send(record);

        CompletableFuture<SendResult<String, Metric>> completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return future.get();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

        completableFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                log.warn("MetricService.insert - message failed! metric: " + metric.toString());
            } else {
                log.info("MetricService.insert - message success! metric: " + metric.toString());
            }
        });
    }

    /**
     * Sends an update operation message for the given metric to Kafka.
     *
     * @param metric The metric to update.
     */
    public void update(Metric metric) {
        var record = new ProducerRecord<>(KafkaTopics.METRICS_TOPIC, metric.getId(), metric);
        record.headers().add(KafkaHeaders.CONSUMER_OPERATION, "update".getBytes());
        var future = kafka.send(record);

        CompletableFuture<SendResult<String, Metric>> completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return future.get();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

        completableFuture.whenComplete((result, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                log.warn("MetricService.update - message failed! metric: " + metric.toString());
            } else {
                log.info("MetricService.update - message success! metric: " + metric.toString());
            }
        });
    }
}
