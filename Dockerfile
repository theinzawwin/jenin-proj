FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar trust-spring.jar
ENTRYPOINT ["java", "-jar","trust-spring.jar"]
