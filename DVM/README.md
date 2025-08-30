# Dealer and Vehicle Management System

## Overview
This project is a Spring Boot-based application designed to manage dealers and their associated vehicles. It provides RESTful APIs for CRUD operations on dealers and vehicles, with proper exception handling, standardized API responses, and Swagger documentation for easy API exploration.

## Features
- **Dealer Management**:
  - Create, Read, Update, and Delete dealers.
  - Fetch all dealers or a specific dealer by ID.
- **Vehicle Management**:
  - Create, Read, Update, and Delete vehicles.
  - Fetch all vehicles or a specific vehicle by ID.
  - Fetch vehicles associated with premium dealers.
- **Exception Handling**:
  - Global exception handling with meaningful error responses.
- **Standardized API Responses**:
  - Consistent structure for success and error responses.
- **Swagger Integration**:
  - Interactive API documentation and testing.
- **Database Integration**:
  - Uses PostgreSQL for data persistence with JPA and Hibernate.

## Technologies Used
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **IDE**: IntelliJ IDEA
- **Version Control**: GitHub

## Prerequisites
- **Java**: Version 21
- **Maven**: Version 3.6 or higher
- **Database**: PostgreSQL (or any SQL database)
- **Environment Variables**:
  - `DB_HOST`: Database connection URL (e.g., `jdbc:postgresql://localhost:5432/your_database`)
  - `DB_USERNAME`: Database username
  - `DB_PASSWORD`: Database password
  - `DB_SCHEMA`: Database schema (e.g., `bluadmin`)

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Shagun-Rajput/DVM.git
   cd DVM
   ```
2. **Configure the Application**:
   Update the `application.yml` file with your database details:
   ```yaml
   spring:
     datasource:
       url: ${DB_HOST} # Replace with your database URL
       username: ${DB_USERNAME} # Replace with your database username
       password: ${DB_PASSWORD} # Replace with your database password
       driver-class-name: org.postgresql.Driver
     jpa:
       hibernate:
         ddl-auto: update # Controls schema generation
       show-sql: true
       database-platform: org.hibernate.dialect.PostgreSQLDialect
       properties:
         hibernate.default_schema: ${DB_SCHEMA} # Replace with your schema name
   
3. **Build the Project**:
   ```bash
   mvn clean install
   ```
4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
5. **Access the Application**:
   - The application will be running at `http://localhost:8085/dvm`.
6. **Apis collection at directory**:
   - Postman collection is available in the `DVM/docs/APIs-Collection.json` file`
   
      