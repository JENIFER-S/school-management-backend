# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . .
# Intha line-ah add pannunga (mvnw-ku permission tharuvathu)
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests