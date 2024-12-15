FROM openjdk:17-jdk-alpine AS build
RUN apk add --no-cache maven
WORKDIR /app
COPY ./ .
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir -p /var/log && chown spring:spring /var/log
USER spring:spring
COPY --from=build /app/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
