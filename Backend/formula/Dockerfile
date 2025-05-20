FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

RUN apt-get update && \
    apt-get install -y maven dos2unix && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN dos2unix mvnw && chmod +x mvnw

RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests && \
    mkdir -p target/dependency && \
    (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:21-jdk-jammy
COPY --from=builder /app/target/dependency/BOOT-INF/lib /app/lib
COPY --from=builder /app/target/dependency/META-INF /app/META-INF
COPY --from=builder /app/target/dependency/BOOT-INF/classes /app

EXPOSE 8080
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "formula.formula.FormulaApplication"]