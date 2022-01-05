FROM openjdk:17
WORKDIR /app
COPY target/TransactionService-0.0.1-SNAPSHOT.jar /app
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=default","TransactionService-0.0.1-SNAPSHOT.jar"]
