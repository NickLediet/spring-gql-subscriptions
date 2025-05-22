# Spring Boot WebFlux with PostgreSQL

A simple Spring Boot WebFlux application with PostgreSQL database using Podman.

## Prerequisites

- Java 21
- Maven
- Podman

## Running the Application

1. Start the application using the provided script:

```bash
./start.sh
```

This script will:
- Start a PostgreSQL container using Podman
- Wait for the database to initialize
- Launch the Spring Boot application

2. Access the application at http://localhost:8080

## Stopping the Application

To stop the application and clean up resources:

1. Stop the Spring Boot application with Ctrl+C (if running in terminal)
2. Run the stop script to clean up the Podman container:

```bash
./stop.sh
```

## Manual Steps

If you prefer to run the commands manually:

1. Start PostgreSQL with Podman:
```bash
podman run -d \
  --name spring-postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=springdb \
  -p 5432:5432 \
  postgres:16
```

2. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

3. When finished, stop and remove the container:
```bash
podman stop spring-postgres
podman rm spring-postgres
```

## Configuration

- PostgreSQL connection details are in `src/main/resources/application.properties` 