# Money Bin Inc

# Overview

The application is hosted **[here](https://moneybininc.onrender.com/)**!

This guide provides instructions on how to start and manage the Money Bin Inc. application, a web-based platform comprising a React-based frontend and a Spring Boot backend.

#### Project Structure
- **Frontend**: Developed using React, located in the `/frontend` directory. It provides the user interface for the application.
- **Backend**: Implemented using Spring Boot, located in the `/backend` directory. It handles API requests and serves the static files for the frontend.

#### Starting the Application
1. **Docker**:
   - Run: `docker build -t moneybininc .`
   - Start the Spring Boot application: `docker run -p 10000:10000 moneybininc`
   - Now the application should run on `http://localhost:10000/`.
2. **Start the Backend through Gradle**:
   - Navigate to the backend directory: `cd backend/`
   - Start the Spring Boot application: `./gradlew BootRun`
   - This command starts the backend server, which also serves the frontend static files.

3. **Updating the Frontend**:
   - To update the frontend, first navigate to the frontend directory: `cd frontend/`
   - Run the build command: `npm run build`
   - This command compiles and builds the frontend assets.
   - Copy the built frontend files to the backend static resources directory:
     ```bash
     cp -r frontend/dist/. backend/src/main/resources/static/
     ```
   - This ensures that the updated frontend is served by the Spring Boot application.

#### General Application Build Process
- The frontend React application is built separately from the backend.
- The `npm run build` command in the frontend directory generates a production build of the React application.
- The built static files are then copied to the `backend/src/main/resources/static/` directory. This is a standard location from where Spring Boot serves static files.
- The backend is started using the `./gradlew BootRun` command. It compiles the Spring Boot application and starts serving both the API endpoints and the static content (frontend) on the specified port, typically `8080`.

#### Important Notes
- Ensure that Node.js and npm are installed for building the frontend.
- Java and Gradle are required to run the backend Spring Boot application.
- Any changes made to the frontend must be rebuilt and copied to the backend static directory to reflect in the running application.
