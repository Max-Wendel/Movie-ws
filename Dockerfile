FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/movies-ws-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]