# Build the React application
FROM node:latest as build-frontend
WORKDIR /app
COPY frontend/package.json frontend/package-lock.json* ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# Build the Spring Boot application
FROM eclipse-temurin:17-jdk-focal as build-backend
WORKDIR /app
# Install findutils if necessary
RUN apt-get update && apt-get install -y findutils
# Copy the Gradle Wrapper files correctly
COPY backend/gradlew backend/gradlew.bat ./
COPY backend/gradle gradle
COPY backend/build.gradle backend/settings.gradle ./
COPY backend/src ./src
# Make sure the gradlew script is executable
RUN chmod +x ./gradlew
RUN ./gradlew build --info || ./gradlew build --stacktrace

# Copy the React build output to the static resources directory in the Spring Boot application
#RUN ls -la /app
#COPY --from=build-frontend /app/dist /app/src/main/resources/static

# Final stage: Create the Docker container with the Spring Boot application
FROM openjdk:17  
COPY --from=build-backend /app/build/libs/*.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java","-jar","/app.jar"]