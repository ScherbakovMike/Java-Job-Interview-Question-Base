FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/service3-*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]