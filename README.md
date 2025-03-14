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

## API Endpoints

### **API Gateway** (Entry Point)
- **Base URL**: `http://localhost:8080/`

### **1. Question Service**

- **Get Random Questions**
  - `GET /api/questions/random/{count}`
  - Example: Fetch 5 random questions:
    ```bash
    curl http://localhost:8080/api/questions/random/5
    ```

- **Add a New Question**
  - `POST /api/questions/add`
  - Example:
    ```bash
    curl -X POST http://localhost:8080/api/questions/add -H "Content-Type: application/json" -d '{
      "category": "General Knowledge", "difficulty": "easy", "questionText": "What is the capital of France?", 
      "option1": "Berlin", "option2": "Madrid", "option3": "Paris", "option4": "Rome", "correctAnswer": "option3"
    }'
    ```

- **Get Questions by IDs**
  - `POST /api/questions/by-ids`
  - Example:
    ```bash
    curl -X POST http://localhost:8080/api/questions/by-ids -H "Content-Type: application/json" -d '[1, 2, 3]'
    ```

### **2. Quiz Service**

- **Start a New Quiz**
  - `POST /api/quiz/start`
  - Example:
    ```bash
    curl -X POST http://localhost:8080/api/quiz/start
    ```

- **Submit Quiz and Get Score**
  - `POST /api/quiz/submit/{quizId}`
  - Example:
    ```bash
    curl -X POST http://localhost:8080/api/quiz/submit/1 -H "Content-Type: application/json" -d '{
      "1": "option1", "2": "option3", "3": "option4", "4": "option2", "5": "option1", "6": "option4", 
      "7": "option3", "8": "option1", "9": "option2", "10": "option3"
    }'
    ```

### **Error Handling**
- Invalid requests return an error with a relevant message and HTTP status code, e.g., `400 Bad Request`.

---

Test these endpoints using **cURL**, **Postman**, or any HTTP client. Make sure the API Gateway is running on `http://localhost:8080`.

## Requirements

- Java 17 or later
- Maven 3.6+ (for building the project)
- Docker (optional, for running services in containers)

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/NaikRitesh/Quiz-App-Microservice-Architecture-.git
   cd quiz-app
