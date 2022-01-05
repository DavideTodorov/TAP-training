FROM openjdk:17
WORKDIR /app
COPY target/UserService-0.0.1-SNAPSHOT.jar /app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "UserService-0.0.1-SNAPSHOT.jar"]