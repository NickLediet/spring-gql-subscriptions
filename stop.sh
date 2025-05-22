#!/usr/bin/env bash

# Stop Spring Boot application (assuming it's running in the foreground)
echo "You'll need to manually stop the Spring Boot application (Ctrl+C)"

# Stop and remove PostgreSQL container
echo "Stopping and removing PostgreSQL container..."
podman stop spring-postgres
podman rm spring-postgres 