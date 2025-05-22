#!/usr/bin/env bash

# Start PostgreSQL container with Podman
echo "Starting PostgreSQL container with Podman..."
podman run -d \
  --name spring-postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=springdb \
  -p 5432:5432 \
  postgres:16

# Wait for PostgreSQL to start
echo "Waiting for PostgreSQL to start..."
sleep 5

# Start Spring Boot application
echo "Starting Spring Boot application..."
mvn clean install spring-boot:run  -DskipTests=true