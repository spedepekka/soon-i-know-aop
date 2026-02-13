# service-a-api

Kotlin Spring Boot API service. Exposes `/api/hello` endpoint.

## Build

```sh
gradle build
```

## Run locally

```sh
gradle bootRun
```

## Docker

Build the JAR and Docker image:

```sh
gradle build
# Then build the Docker image
# docker build -t sooniknow/service-a-api:latest .
```
