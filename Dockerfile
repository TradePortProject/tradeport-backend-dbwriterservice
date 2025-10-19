# Use a specific OpenJDK version as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR into the container
# Assuming you build your JAR with Maven and it's in the 'target' directory
COPY target/*.jar app.jar

# Expose the port your Spring Boot application runs on (default Spring Boot port)
EXPOSE 9099

# Define environment variables that your application might need
# These can be overridden in the docker-compose.yml
ENV SPRING_DATASOURCE_URL=jdbc:sqlserver://localhost:1433;databaseName=your_db_name
ENV SPRING_DATASOURCE_USERNAME=your_db_user
ENV SPRING_DATASOURCE_PASSWORD=your_db_password
ENV SPRING_KAFKA_BOOTSTRAP_SERVERS=my-kafka-cluster-kafka-bootstrap.kafka-namespace:9092
ENV KAFKA_TOPIC=your_kafka_topic

# Define the command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
