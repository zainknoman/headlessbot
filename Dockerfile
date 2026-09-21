FROM mcr.microsoft.com/playwright/java:v1.47.0-jammy
WORKDIR /app
COPY target/form-bot-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
