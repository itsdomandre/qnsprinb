FROM amazoncorretto:17-alpine AS build
RUN apk add --no-cache maven
WORKDIR /app
COPY app ./
RUN mvn clean package -DskipTests
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir -p /var/log && chown spring:spring /var/log
USER spring:spring
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]