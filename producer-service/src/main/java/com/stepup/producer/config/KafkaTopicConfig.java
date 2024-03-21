package com.stepup.producer.config;

import com.stepup.common.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * The {@code KafkaTopicConfig} class is a Spring configuration class responsible for defining
 * Kafka topic configurations for producer application.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Defines a new Kafka topic for storing metrics data.
     *
     * @return A {@code NewTopic} instance representing the Kafka topic configuration.
     */
    @Bean
    public NewTopic metric() {
        return TopicBuilder.name(KafkaTopics.METRICS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
