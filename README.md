# Commerce Platform Backend

Commerce Platform Backend is a microservices-based backend project built with Spring Boot and Spring Cloud.  
It was designed as a portfolio project to demonstrate backend development skills in distributed systems, inter-service communication, fault tolerance, centralized configuration, and API design.

## Project Overview

This project simulates the backend of a commerce platform where products, inventory, carts, and orders are managed through independent microservices.

The system currently includes:

- Service discovery with Eureka
- API Gateway as a single entry point
- Inter-service communication using OpenFeign
- Load balancing with multiple service instances
- Fault tolerance with Circuit Breaker
- Centralized configuration with Config Server
- Persistence with MySQL
- Validation and global exception handling
- Basic business workflows for cart and order management

## Architecture

The project is organized as a microservices architecture with the following services:

### Infrastructure services
- **discovery-service**: Eureka Server for service registration and discovery
- **gateway-service**: API Gateway for routing external requests
- **config-service**: Centralized configuration server

### Business services
- **product-service**: Manages product catalog data
- **inventory-service**: Manages stock and inventory validation
- **cart-service**: Manages shopping carts and cart items
- **order-service**: Manages order creation, validation, total calculation, and inventory updates

## Current Features

### Product Service
- Product CRUD operations
- Request validation
- Global exception handling
- MySQL persistence
- Configuration loaded from Config Server

### Inventory Service
- Inventory CRUD operations
- Stock validation by product
- Inventory discount after successful order creation
- Global exception handling
- MySQL persistence
- Configuration loaded from Config Server

### Cart Service
- Cart and cart item CRUD operations
- Validation of product existence through `product-service`
- Circuit Breaker protection for remote product validation
- Global exception handling
- MySQL persistence
- Configuration loaded from Config Server

### Order Service
- Order and order item CRUD base
- Validation of product existence through `product-service`
- Validation of stock availability through `inventory-service`
- Automatic inventory discount for paid orders
- Order total calculation in service layer
- Order status handling with enum:
  - `PENDING`
  - `PAID`
  - `CANCELLED`
- Circuit Breaker protection for remote product and inventory calls
- Global exception handling
- MySQL persistence
- Configuration loaded from Config Server

## Spring Cloud Components Implemented

This project currently includes the following Spring Cloud patterns and components:

- **Service Registry / Discovery** with Eureka
- **API Gateway**
- **Load Balancing**
- **Circuit Breaker**
- **Config Server**
- **OpenFeign** for service-to-service communication

## Technologies Used

- Java 17
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Spring Cloud OpenFeign
- Spring Cloud Config Server / Config Client
- Resilience4j
- MySQL
- Lombok
- Jakarta Validation
- Maven
- Postman
- Git / GitHub

## Service Communication

Current service-to-service communication includes:

- `cart-service` -> `product-service`
- `order-service` -> `product-service`
- `order-service` -> `inventory-service`

## Centralized Configuration

Configuration is managed through:

- **config-service**
- external configuration repository: `commerce-platform-config-repo`

This allows services to load their properties from a centralized source instead of relying only on local `application.properties` files.

## Business Workflow Implemented

### Order creation flow
When creating an order:

1. `order-service` validates that all requested products exist through `product-service`
2. `order-service` validates stock through `inventory-service`
3. the order total is calculated internally
4. if the order status is `PAID`, inventory is discounted
5. the order is saved with its associated order items

### Cart validation flow
When creating or updating a cart:

1. `cart-service` extracts the product IDs from cart items
2. `product-service` validates that those products exist
3. if one or more products do not exist, the request is rejected

## Error Handling

Global exception handling is implemented across services using `@RestControllerAdvice`.

Handled scenarios include:
- entity not found
- validation errors
- insufficient stock
- unavailable remote services through circuit breaker fallback

## Project Status

At this stage, the project already covers a strong portion of the Spring Boot + Spring Cloud backend stack.

Implemented:
- microservices architecture
- distributed communication
- resilience patterns
- centralized config
- business logic integration

Pending / next steps:
- `user-service`
- `auth-service`
- Spring Security
- JWT authentication and authorization
- role-based access
- testing
- Docker / docker-compose
- final documentation improvements

## Repository Structure

```text
commerce-platform-backend/
├── discovery-service/
├── gateway-service/
├── config-service/
├── product-service/
├── inventory-service/
├── cart-service/
├── order-service/
├── docs/
└── README.md
