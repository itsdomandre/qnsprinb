# Etapa de construção (build)
FROM maven:3.9.0-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de execução (run)
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/qnspringb-1.0-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]
