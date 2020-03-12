FROM openjdk:8-jdk-alpine
VOLUME /Users/zero/docker_dir
ADD message-server-DEVELOP-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]