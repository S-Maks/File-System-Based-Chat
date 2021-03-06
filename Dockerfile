FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/File-System-Based-Chat-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
