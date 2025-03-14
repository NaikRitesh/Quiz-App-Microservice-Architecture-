# Quiz-App-Microservice-Architecture-

This is a Spring Boot-based microservices application consisting of four services: Eureka Server, Question Service, Quiz Service, and an API Gateway. The application allows users to take quizzes based on randomly fetched questions from the Question Service. It uses Netflix Eureka for service discovery, Feign for communication between services, and an API Gateway to route requests to the appropriate services.

## Services Overview

1. **Eureka Server**: 
   - Acts as the service registry for discovering and managing services.
   - Runs on port `8761`.

2. **Question Service**:
   - Manages questions and provides APIs to fetch random questions or add new questions.
   - Runs on port `8081`.
   - Registers itself with Eureka for discovery.

3. **Quiz Service**:
   - Manages quizzes by fetching questions from the Question Service and calculating scores.
   - Runs on port `8082`.
   - Registers itself with Eureka for discovery and uses Feign Client to interact with the Question Service.

4. **API Gateway**:
   - Routes incoming requests to the appropriate microservice (Question Service or Quiz Service).
   - Acts as a reverse proxy and simplifies the interaction for the client.
   - Runs on port `8080`.

## Requirements

- Java 17 or later
- Maven 3.6+ (for building the project)
- Docker (optional, for running services in containers)

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/quiz-app.git
   cd quiz-app
