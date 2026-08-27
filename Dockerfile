FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/*.jar app.jar

ENV DB_URL=jdbc:oracle:thin:@host.docker.internal:1521/FREE
ENV DB_USERNAME=SYSTEM
ENV DB_PASSWORD=admin123

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]