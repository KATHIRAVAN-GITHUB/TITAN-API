FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/Titan-0.0.1-SNAPSHOT.jar demo.jar
#EXPOSE 2002
#ENTRYPOINT ["java","-jar","demo.jar"]

# === Stage 1: Build ===
#FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# === Stage 2: Run ===
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose app port
EXPOSE 2002

# Use environment variables passed from .env
ENTRYPOINT ["java", "-jar", "app.jar"]