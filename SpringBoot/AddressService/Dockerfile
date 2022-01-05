FROM openjdk:17
WORKDIR /app
COPY target/AddressService-0.0.1-SNAPSHOT.jar /app
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=default", "AddressService-0.0.1-SNAPSHOT.jar"]