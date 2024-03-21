# Dockerfile for building a Docker image to run a Java application.
#
# The official OpenJDK 17 JDK as the base image.
FROM openjdk:17-jdk
ADD target/consumer-service.jar consumer-service.jar
ENTRYPOINT ["java","-jar","/consumer-service.jar"]