FROM maven:3.8.7-eclipse-temurin-17 AS builder

COPY ./ ./

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/dependency/BOOT-INF/lib /app/lib
COPY --from=builder /app/target/dependency/META-INF /app/META-INF
COPY --from=builder /app/target/dependency/BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java", "-cp", "/app:/app/lib/*", "formula.formula.FormulaApplication"]
