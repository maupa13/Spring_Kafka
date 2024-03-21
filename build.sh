#!/bin/sh
./mvnw clean package
docker build -t /spring-restful-kafka-consumer ./consumer
docker build -t /spring-restful-kafka-producer ./producer
docker push /spring-restful-kafka-consumer
docker push /spring-restful-kafka-producer