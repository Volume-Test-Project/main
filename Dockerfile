FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} demo-0.0.1-SNAPSHOT.jar

LABEL authors="jgone2"

ENTRYPOINT ["top", "-b"]