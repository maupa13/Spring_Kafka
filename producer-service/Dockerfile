# Dockerfile for building a Docker image to run a Java application.
#
# The official OpenJDK 17 JDK as the base image.
FROM openjdk:17-jdk
ADD target/producer-service.jar producer-service.jar
ENTRYPOINT ["java","-jar","/producer-service.jar"]