FROM maven:3.5.0-jdk-21-slim AS builder

WORKDIR /app

COPY ./ ./

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/dependency/BOOT-INF/lib /app/lib
COPY --from=builder /app/target/dependency/META-INF /app/META-INF
COPY --from=builder /app/target/dependency/BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java", "-cp", "/app:/app/lib/*", "formula.formula.FormulaApplication"]
