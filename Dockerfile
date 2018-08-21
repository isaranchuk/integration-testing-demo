FROM openjdk:8-jdk-alpine

COPY target/*.jar /app.jar

RUN touch /app.jar

VOLUME /tmp

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-Xms256m", "-Xms256m", "-jar", "app.jar"]
