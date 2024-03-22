package com.stepup.consumer.kafka;

import com.stepup.consumer.model.MetricDto;
import com.stepup.common.KafkaHeaders;
import com.stepup.common.KafkaTopics;
import com.stepup.consumer.service.MetricService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * The {@code MetricListener} class is a Spring component responsible
 * for listening to Kafka messages related to metrics.
 */
@Component
@AllArgsConstructor
@Slf4j
public class MetricListener {

    private final MetricService service;

    /**
     * Listens to messages from the Kafka metrics topic and processes
     * them based on the operation specified in the CONSUMER_OPERATION header.
     *
     * @param metricDto The metricDto received from Kafka.
     * @param operation The operation specified in the CONSUMER_OPERATION header.
     */
    @KafkaListener(topics = KafkaTopics.METRICS_TOPIC)
    public void listen(
            @Payload MetricDto metricDto,
            @Header(KafkaHeaders.CONSUMER_OPERATION) String operation) {
        if ("insert".equals(operation)) {
            service.insert(metricDto);
        } else {
            log.warn("MetricListener.listen has a invalid CONSUMER_OPERATION header " + operation);
        }
    }
}
