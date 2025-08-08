# Convertly - Unit Converter REST API

A clean and modular Spring Boot REST API that converts values between units of:

- Temperature
- Length
- Weight
- Time

## Features

-  POST `/convert` — Convert value
-  GET `/categories` — List all categories
-  GET `/units?category=...` — Units by category
-  GET `/sample-payload` — Sample request body
-  GET `/health` — Health check

Built with:
- Spring Boot
- Java
- Maven

## Run Locally

```bash
mvn spring-boot:run
