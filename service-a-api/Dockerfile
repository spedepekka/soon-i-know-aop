FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/service-a-api.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
