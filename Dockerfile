FROM node:20-slim AS frontend-build
WORKDIR /app/frontend

COPY integratehub-frontend/package*.json ./
RUN npm ci

COPY integratehub-frontend/ ./
RUN npm run build

# Build backend with embedded static assets
FROM maven:3.9.9-eclipse-temurin-17 AS backend-build
WORKDIR /app

COPY backend/pom.xml backend/pom.xml
COPY backend/src backend/src
RUN mkdir -p backend/src/main/resources/static
COPY --from=frontend-build /app/frontend/dist/. backend/src/main/resources/static/

RUN mvn -f backend/pom.xml -DskipTests clean package

# Runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=backend-build /app/backend/target/integratehub-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]