FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY target/*-shaded.jar app.jar

COPY src/main/resources/features/* \
    /test/features/

ENTRYPOINT java -jar app.jar --plugin "pretty" --glue "classpath:com.isaranchuk" /test/features/