# Build the React application
FROM node:latest as build-frontend
WORKDIR /app
COPY frontend/package.json frontend/package-lock.json* ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# Build the Spring Boot application
FROM openjdk:17 as build-backend
WORKDIR /app

# Make sure the path here matches the structure of the build-frontend stage
COPY --from=build-frontend /app/dist /public  

FROM eclipse-temurin:17-jdk-focal as build-backend

WORKDIR /app
# Install findutils if necessary
RUN apt-get update && apt-get install -y findutils
# Copy the Gradle Wrapper files correctly
COPY backend/src/main/resources /app/src/main/resources
COPY backend/gradlew backend/gradlew.bat ./
COPY backend/gradle gradle
COPY backend/build.gradle backend/settings.gradle ./
COPY backend/src ./src
# Make sure the gradlew script is executable
RUN chmod +x ./gradlew
RUN ./gradlew build --info || ./gradlew build --stacktrace

# Final stage: Create the Docker container with the Spring Boot application
FROM openjdk:17  
COPY --from=build-backend /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
