# Use a base image with Java 21
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/JobInterviewBase-0.0.1-SNAPSHOT.jar app.jar

# Create a directory for logs and a user to avoid running as root
RUN mkdir logs && chown 1001 logs
USER 1001

# Expose the port
EXPOSE 8080

# Entrypoint. Use $JAVA_TOOL_OPTIONS to pass any JVM flags
ENTRYPOINT ["java", "-jar", "/app/app.jar"]