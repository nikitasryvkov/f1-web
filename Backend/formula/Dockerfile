FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

RUN mvn -N io.takari:maven:wrapper

COPY ./mvnw .

RUN dos2unix ./mvnw

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline -B

COPY ./src ./src

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:21-jre-alpine AS runner

COPY --from=builder /app/target/dependency/BOOT-INF/lib /app/lib
COPY --from=builder /app/target/dependency/META-INF /app/META-INF
COPY --from=builder /app/target/dependency/BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "formula.formula.FormulaApplication"]