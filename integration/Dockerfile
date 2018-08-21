FROM openjdk:8-jdk-alpine

COPY waitfor.sh waitfor.sh

COPY target/*-shaded.jar app.jar

COPY src/main/resources/features/* \
    /test/features/

CMD ["java"]
