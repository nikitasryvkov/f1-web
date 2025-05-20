# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]