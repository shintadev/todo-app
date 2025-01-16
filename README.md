# Todo App

This is a full-stack Todo application built with React for the frontend and Spring Boot for the backend.

## Prerequisites

- Node.js (latest version)
- npm (latest version)
- Java JDK (version 23 or later)
- Maven (latest version)
- Docker (optional, for containerized deployment)

## Getting Started

1. Run the Docker Compose command to build and run the application:
    ```sh
    docker compose -f docker-compose.yml up -d --build
    ```

2. Open your browser and go to `http://localhost:3000` for the application.

## Project Structure

- `todo-app-fe`: Frontend code (React)
- `todo-app-be`: Backend code (Spring Boot)
- `Dockerfile`: Docker configuration for building the application
- `docker-compose.yml`: Docker Compose configuration for running the application

## License

This project is licensed under the MIT License.