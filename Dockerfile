# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file into the Docker image
COPY target/rock.paper.scissors-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Set environment variables (optional, if needed)
# ENV SPRING_PROFILES_ACTIVE prod

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
