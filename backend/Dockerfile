# Use Maven image to build the app
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /build

# Copy Maven files and source code
COPY pom.xml .
COPY src ./src

# Build the app
RUN mvn clean package -DskipTests

# ------------------------

# Now run using a lightweight Java runtime
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy JAR
COPY target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]