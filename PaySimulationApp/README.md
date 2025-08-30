# Payment Simulation Application

## Overview
This Spring Boot-based payment application provides APIs for managing payments, including initiating payments, simulating callbacks, JWT-based authentication, and request interception for security.

---

## Features

### 1. **Payment Management**
- **Initiate Payment**:
    - Endpoint: `/api/payment/initiate`
    - Accepts `dealerId`, `amount`, and `method` (e.g., UPI, Card, NetBanking).
    - Saves the payment record with a status of `PENDING`.
    - Simulates a callback to update the status to `SUCCESS` after 5 seconds.

- **Find Payments**:
    - Endpoint: `/api/payment/find`
    - Supports filtering by `id`, `dealerId`, `amount`, `method`, and `status`.
    - Includes pagination and metadata in the response.

---

### 2. **JWT Authentication**
- **Generate JWT Token**:
    - Generates a Base64-encoded JWT token with the following claims:
        - `userId`
        - `username`
        - `email`
        - `iat` (issued at)
        - `exp` (expiration, 10 minutes validity)
    - Uses HMAC-SHA256 for signing.
    - **Note**: User login and registration functionality is not implemented. JWT tokens are generated manually by providing user information in the request body.

- **Decode JWT Token**:
    - Decodes the JWT token to extract claims.
    - Validates the token's signature and expiration.

---

### 3. **Request Interception**
- **JWT Interceptor**:
    - Intercepts all requests except specified endpoints (e.g., `/auth/login`, `/auth/register`).
    - Validates the `auth-token` header for a valid JWT token.
    - Decodes the token and sets claims into the request attributes.

---

## API Endpoints

### **Authentication**
- **Generate JWT Token**: `/auth/generate-token`
    - Accepts user details and generates a JWT token.
    - **Note**: User login and registration are not implemented.

### **Payment**
- **Initiate Payment**: `/api/payment/initiate`
- **Find Payments**: `/api/payment/find`

---

## Technologies Used
- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Maven**: Dependency management.
- **H2 Database**: In-memory database for development.
- **JWT**: Authentication and authorization.
- **Spring Data JPA**: Database interaction.
- **Spring AOP**: For request interception.
- **Spring Async**: For handling delayed operations.

---

## How to Run
1. Clone the repository.
2. Navigate to the project directory.
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the APIs via Postman or any HTTP client.

---

## Configuration
- **JWT Secret Key**: Set in the `application.properties` file.
- **Database**: H2 in-memory database is used by default.

---


## How to Run
1. Clone the repository.
2. Navigate to the project directory.
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the APIs via Postman or any HTTP client.

---

## Configuration
- **JWT Secret Key**: Set in the `application.yml` file.
- **Database**: H2 in-memory database is used by default.

---

## Example Usage

### **Initiate Payment**
Request:
```http
POST /api/payment/initiate
Content-Type: application/json
auth-token:  <jwt-token>
```
Body:
```json
{
  "dealerId": "12345",
  "amount": 1000.0,
  "method": "UPI"
}
```

Response:
```json
{
  "message": "Payment record created with status PENDING.",
  "data": {
    "id": 1,
    "dealerId": "12345",
    "amount": 1000.0,
    "method": "UPI",
    "status": "PENDING"
  }
}
```

---

### **Find Payments**
Request:
```http
GET /api/payment/find?page=0&size=10
auth-token:  <jwt-token>
```

Response:
```json
{
  "message": "Payment details fetched successfully",
  "data": [
    {
      "id": 1,
      "dealerId": "12345",
      "amount": 1000.0,
      "method": "UPI",
      "status": "SUCCESS"
    }
  ],
  "meta": {
    "page": 0,
    "size": 10,
    "totalElements": 1,
    "totalPages": 1
  }
}
```
## Example Usage

### **Generate JWT Token**
Request:
```http
POST /auth/generate-token
Content-Type: application/json
```
Body:
```json
{
  "userId": "1",
  "username": "testuser",
  "email": "testuser@example.com"
}
```
Response:
```json
{
  "message": "JWT token generated successfully",
  "data": "<jwt-token>"
}
```

---

## Security
- **JWT Authentication**: Ensures secure access to APIs.
- **Interceptor**: Validates requests and blocks unauthorized access.

---

## Future Enhancements
- User registration and login functionality.
- Add support for multiple payment methods.
- Implement role-based access control.
- Integrate with external payment gateways.