FROM openjdk:17-jdk-alpine AS builder
WORKDIR /app
COPY app ./
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/qnspringb-1.0-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]
