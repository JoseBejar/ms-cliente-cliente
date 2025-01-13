FROM openjdk:17-jdk-slim

MAINTAINER pruebas.com

RUN groupadd -r spring && useradd --no-log-init -r -g spring spring
USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-cliente-cliente-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/ms-cliente-cliente-0.0.1-SNAPSHOT.jar"]