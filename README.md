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

## Input Validation

- Ensures required fields are present.
- Checks units match the selected category.
- Rejects negative values for categories that cannot be negative (length, weight, time).

## Detailed Response

Returns: 
- Converted result
- Readable formula used
- Status message

Built with:
- Spring Boot
- Java
- Maven
