# Build the React application
FROM node:latest as build-frontend
WORKDIR /app
COPY frontend/package.json frontend/package-lock.json* ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# Build the Spring Boot application
FROM openjdk:11 as build-backend
WORKDIR /app
COPY --from=build-frontend /app/build /public
COPY backend/gradlew backend/gradlew.bat backend/gradle backend/build.gradle backend/settings.gradle ./
COPY backend/src ./src
RUN ./gradlew build

# Final stage: Create the Docker container with the Spring Boot application
FROM openjdk:11-jre
COPY --from=build-backend /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
