# Wallet Service API

## Overview

Wallet Service is a Spring Boot backend application that provides secure wallet operations such as:

- Deposit money
- Withdraw money
- Check wallet balance
- Handle insufficient balance scenarios
- Prevent race conditions using pessimistic locking
- Manage database schema using Liquibase

The project uses PostgreSQL as the database and follows production-style backend practices.

---

# Technologies Used

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Spring Boot | Backend framework |
| Spring Web | REST APIs |
| Spring Data JPA | Database interaction |
| PostgreSQL | Database |
| Liquibase | Database migration/versioning |
| Maven | Dependency management |
| Docker | Containerization |
| Lombok | Boilerplate code reduction |

---

# Features

## Wallet Operations

- Deposit money into wallet
- Withdraw money from wallet
- Fetch wallet balance

## Exception Handling

- Wallet not found
- Insufficient balance
- Invalid request
- Invalid JSON request

## Concurrency Handling

- Pessimistic locking implemented
- Prevents race conditions
- Prevents duplicate concurrent updates

## Database Management

- Liquibase migration integration
- Automatic database schema versioning

---

# Project Structure

```text
src/main/java/com/wallet/walletservice
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── enums

src/main/resources
│
├── application.properties
└── db
    └── changelog
        └── db.changelog-master.xml
```

---

# API Endpoints

## 1. Get Wallet Balance

### Request

```http
GET /api/v1/wallets/{walletId}
```

### Example

```http
GET /api/v1/wallets/550e8400-e29b-41d4-a716-446655440000
```

### Response

```json
10000.00
```

---

# 2. Deposit Money

### Request

```http
POST /api/v1/wallet
```

### Request Body

```json
{
  "walletId": "550e8400-e29b-41d4-a716-446655440000",
  "operationType": "DEPOSIT",
  "amount": 500
}
```

### Response

```text
Wallet updated successfully
```

---

# 3. Withdraw Money

### Request Body

```json
{
  "walletId": "550e8400-e29b-41d4-a716-446655440000",
  "operationType": "WITHDRAW",
  "amount": 500
}
```

### Response

```text
Wallet updated successfully
```

---

# Error Responses

## Insufficient Balance

```json
{
  "message": "Insufficient balance",
  "timestamp": "2026-05-16T03:06:59.4227014",
  "status": 400
}
```

---

## Wallet Not Found

```json
{
  "message": "Wallet not found",
  "status": 404
}
```

---

## Invalid JSON Request

```json
{
  "message": "Invalid JSON request",
  "status": 400
}
```

---

# Liquibase Integration

Liquibase is used for database migration and schema version control.

## Liquibase File

```text
src/main/resources/db/changelog/db.changelog-master.xml
```

## Responsibilities

- Creates database tables automatically
- Tracks migration history
- Prevents duplicate schema updates
- Maintains database versioning

---

# Pessimistic Locking

Implemented using:

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
```

Purpose:

- Prevent race conditions
- Ensure safe concurrent wallet updates
- Prevent duplicate balance modifications

---

# Database Configuration

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/wallet_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:/db/changelog/db.changelog-master.xml
```

---

# Docker Setup

## Dockerfile

```dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/walletservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# Build Project

```bash
mvn clean install
```

---

# Run Application

```bash
mvn spring-boot:run
```

---

# Build Docker Image

```bash
docker build -t wallet-app .
```

---

# Run Docker Container

```bash
docker run -p 8080:8080 -e "SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/wallet_db" -e "SPRING_DATASOURCE_USERNAME=postgres" -e "SPRING_DATASOURCE_PASSWORD=your_password" wallet-app
```

---

# Future Improvements

- JWT Authentication
- Swagger/OpenAPI documentation
- Unit Testing
- Docker Compose integration
- Transaction history
- User management

---

# Author

Mahammad Yasin Nadaf
