FROM amazoncorretto:17-alpine AS build
RUN apk add --no-cache maven
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY ../src ./src
RUN mvn clean package -DskipTests
FROM amazoncorretto:17-alpine
RUN addgroup -S spring && adduser -S spring -G spring
WORKDIR /app
USER spring:spring
ENTRYPOINT ["java", "-jar", "app.jar"]
