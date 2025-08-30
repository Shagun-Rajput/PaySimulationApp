# Dealer and Vehicle Management System

## Overview
This project is a Spring Boot-based application designed to manage dealers and their associated vehicles. It provides RESTful APIs for CRUD operations on dealers and vehicles, with proper exception handling and standardized API responses.

## Features
- Manage Dealers:
    - Create, Read, Update, and Delete dealers.
    - Fetch all dealers or a specific dealer by ID.
- Manage Vehicles:
    - Create, Read, Update, and Delete vehicles.
    - Fetch all vehicles or a specific vehicle by ID.
    - Fetch vehicles associated with premium dealers.
- Exception Handling:
    - Global exception handling with meaningful error responses.
- Standardized API Responses:
    - Consistent structure for success and error responses.
- Database Integration:
    - Uses SQL for data persistence with JPA and Hibernate.

## Technologies Used
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: SQL (e.g., PostgreSQL)
- **Build Tool**: Maven
- **IDE**: IntelliJ IDEA
- **Version Control**: GitHub

## Prerequisites
- Java 21
- Maven 3.6 or higher
- A running SQL database (e.g., PostgreSQL)

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/Shagun-Rajput/DVM.git