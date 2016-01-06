Drools Demo
===

Course planner application for demonstrating Drools use-cases and configuration.
Rules file in this version is included in source code (without using Drools Workbench).

## Build the application:

    mvn clean install

## Run the application:

    mvn spring-boot:run

## Get Suggested Course/s:

    http://localhost:9999/suggest

## Sample Payload:
    {
    "items": [{"subject": "math", "rating": 10}, {"subject": "physics", "rating": 10}]
    }
