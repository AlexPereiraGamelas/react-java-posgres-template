FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:18-jre
WORKDIR /app
COPY --from=build /app/target/api-1.0.0.jar app.jar
EXPOSE 9090
CMD ["java", "-jar", "app.jar"]