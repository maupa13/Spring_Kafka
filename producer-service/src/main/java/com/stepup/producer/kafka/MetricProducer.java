package com.stepup.producer.kafka;

import com.stepup.common.KafkaHeaders;
import com.stepup.common.KafkaTopics;
import com.stepup.producer.model.MetricDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * The {@code MetricProducer} class is a Spring service responsible
 * for producing Kafka messages related to metrics in the application.
 */
@Service
@AllArgsConstructor
@Slf4j
public class MetricProducer {

    private final KafkaTemplate<String, MetricDto> kafka;


    /**
     * Sends an insert operation message for the given metricDto to Kafka.
     *
     * @param metricDto The metricDto to insert.
     */
    public void insert(MetricDto metricDto) {
        ProducerRecord<String, MetricDto> record = new ProducerRecord<>(KafkaTopics.METRICS_TOPIC, metricDto);
        record.headers().add(KafkaHeaders.CONSUMER_OPERATION, "insert".getBytes());
        var future = kafka.send(record);

        CompletableFuture<SendResult<String, MetricDto>> completableFuture =
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
                log.warn("MetricService.insert - message failed! MetricDto: " + metricDto.toString());
            } else {
                log.info("MetricService.insert - message success! MetricDto: " + metricDto.toString());
            }
        });
    }
}
