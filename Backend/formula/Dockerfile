FROM openjdk:21-jdk-slim AS builder

WORKDIR /app

RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

COPY pom.xml .

RUN mvn -N io.takari:maven:wrapper

COPY ./mvnw .

RUN dos2unix ./mvnw

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline -B

COPY ./src ./src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:21-jdk-slim

COPY --from=builder /app/target/dependency/BOOT-INF/lib /app/lib
COPY --from=builder /app/target/dependency/META-INF /app/META-INF
COPY --from=builder /app/target/dependency/BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "formula.formula.FormulaApplication"]