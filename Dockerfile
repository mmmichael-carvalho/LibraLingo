FROM node:20-alpine AS frontend-build

WORKDIR /app/frontend


COPY frontend/package*.json ./

RUN npm install --legacy-peer-deps

COPY frontend/ ./

RUN npm run build -- --configuration production

FROM maven:3.9-eclipse-temurin-21-alpine AS backend-build

WORKDIR /app/backend

COPY backend/pom.xml ./

RUN mvn dependency:go-offline -B

COPY backend/src ./src

COPY --from=frontend-build /app/frontend/dist/libralingo ./src/main/resources/static

RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=backend-build /app/backend/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]