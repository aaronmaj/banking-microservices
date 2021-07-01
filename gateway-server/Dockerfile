# Use an official OpenJDK runtime as a parent image
FROM openjdk:jdk11-openjdk-slim as builder
RUN mkdir -p /app/source
COPY ../account-service /app/source

# Set the working directory to /app/source
WORKDIR /app/source
RUN ./mvnw clean package

# Copy the jar into the container at /app
FROM builder
COPY --from=builder /app/source/target/*.jar /app
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/account-service.jar"]