# Spring_Kafka Application

The Spring_Kafka (StepUp) application is a Kafka-based system designed for actuator metrics management.
It consists of a Kafka cluster, Postgres database, Spring Boot 3.1.4 services for consuming and producing metrics,
and configurations for Docker-based development environments. 

The application was scheduled to send metrics every 3 minutes in producer-service to consumer-service and database.

## Getting Started
To get started with the Spring_Kafka application, follow these steps:
1. Clone this repository to your local machine.
2. Configure your Kafka security settings, if necessary, in the `kafka_server_jaas.conf` and `zookeeper_jaas.conf` files.
3. Run `docker-compose up` to start the Kafka cluster and Spring Boot services.
4. Access the consumer and producer services via the provided endpoints.

## Project Structure
The project is structured as follows:
- `common`: Contains common Kafka data.
- `consumer-service`: Contains the Spring Boot application for consuming metrics.
- `producer-service`: Contains the Spring Boot application for producing metrics.
- `docker-compose.yml`: Defines the Docker Compose configuration for setting up the Kafka cluster and services.
- `docker-compose-dev.yml`: Defines the Docker Compose configuration for setting up the Kafka cluster, services for development.

## Running the Application
To run the Spring_Kafka application, execute the following command in the project root directory:
    ```bash
    docker-compose up -d

When containers are running, you may access:
1. The consumer-service endpoints at http://localhost:8081/api/v1/metricDto
2. The producer-service endpoints at http://localhost:8082/api/v1/metricDto

##  Swagger UI
To run Swagger UI, go to following path:
- `producer-service`: http://localhost:8082/swagger-ui/index.html
- `consumer-service`: http://localhost:8081/swagger-ui/index.html