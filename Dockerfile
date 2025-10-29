FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app


COPY backend/pom.xml backend/pom.xml
WORKDIR /app/backend
RUN mvn dependency:go-offline -B


WORKDIR /app
COPY backend/src backend/src
WORKDIR /app/backend
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]