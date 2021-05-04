FROM openjdk:12-jdk-alpine
COPY build/libs/zeebe-app-2.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 7003