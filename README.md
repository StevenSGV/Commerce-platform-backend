# Scalable Marketplace Platform Backend with Spring Cloud, Security & JWT

Microservices-based backend project for a commerce platform built with **Java**, **Spring Boot**, and **Spring Cloud**.  
This project was developed as a portfolio application to demonstrate practical backend skills in **distributed architecture**, **inter-service communication**, **centralized configuration**, **fault tolerance**, and **security with Spring Security and JWT**.

## Overview

This system simulates the backend of a commerce platform where products, inventory, carts, and orders are managed through independent microservices.

The architecture is designed around a set of infrastructure services, business services, and security-related services that work together to support both domain operations and protected access to the platform.

From a portfolio perspective, the project highlights not only CRUD development, but also the implementation of real microservices patterns such as service discovery, API Gateway, centralized configuration, remote communication with OpenFeign, resilience with Circuit Breaker, and token-based authentication with JWT.

## Architecture

The project is organized into three main areas: infrastructure, business domain, and security domain.

### Infrastructure Services

- **discovery-service** → Registers and discovers services through **Eureka Server**
- **config-service** → Provides **centralized external configuration** for the microservices
- **gateway-service** → Acts as the **main entry point** to the platform, routing client requests to the corresponding services and containing the central security configuration for request access

### Business Services

- **product-service** → Manages product catalog operations
- **inventory-service** → Manages stock, availability checks, and inventory updates
- **cart-service** → Manages carts and cart items
- **order-service** → Manages order creation, validation, total calculation, and inventory discount workflows

### Security Services

- **auth-service** → Handles authentication and JWT generation/validation
- **user-service** → Manages **users, roles, and permissions**

## How Security Works

Security is distributed across the platform rather than being handled by a single service.

The **gateway-service** works as the main entry door of the system and contains the core **SecurityConfig**, allowing requests to enter the platform under the defined security rules.

The **auth-service** is responsible for authenticating users and generating JWT tokens, as well as validating token-related security flows.

The **user-service** is responsible for the management of security-related domain entities, including:

- **User**
- **Role**
- **Permission**

In addition, each protected microservice implements its own **token authentication filter**, allowing it to validate incoming JWT-based requests and secure its endpoints internally.

This approach reinforces practical understanding of how security can be applied in a distributed system where authentication, authorization, and request protection are shared responsibilities across multiple services.

## Implemented Features

The platform currently includes the following implemented features:

### Infrastructure and Distributed System Features
- Service discovery with **Eureka**
- API Gateway as the platform entry point
- Centralized configuration with **Config Server**
- Inter-service communication using **OpenFeign**
- Load balancing between service instances
- Fault tolerance using **Circuit Breaker**

### Product Service
- Product CRUD operations
- Request validation
- Global exception handling
- MySQL persistence
- Externalized configuration through Config Server

### Inventory Service
- Inventory CRUD operations
- Stock validation by product
- Inventory discount after successful order creation
- Global exception handling
- MySQL persistence
- Externalized configuration through Config Server

### Cart Service
- Cart and cart item CRUD operations
- Product existence validation through `product-service`
- Circuit Breaker protection for remote validation
- Global exception handling
- MySQL persistence
- Externalized configuration through Config Server

### Order Service
- Order and order item CRUD operations
- Product validation through `product-service`
- Stock validation through `inventory-service`
- Automatic inventory discount for paid orders
- Order total calculation in the service layer
- Order status handling with:
  - `PENDING`
  - `PAID`
  - `CANCELLED`
- Circuit Breaker protection for remote calls
- Global exception handling
- MySQL persistence
- Externalized configuration through Config Server

### User Service
- User CRUD operations
- Role CRUD operations
- Permission CRUD operations
- Relationships between **User**, **Role**, and **Permission**
- Validation and persistence of authorization-related entities

### Auth Service
- Authentication with **Spring Security**
- JWT generation and validation
- Login endpoint for token creation
- Secure authentication flow for protected access

## Service Communication

The current inter-service communication includes:

- `cart-service` → `product-service`
- `order-service` → `product-service`
- `order-service` → `inventory-service`

This communication is implemented with **OpenFeign**, allowing services to collaborate while preserving separation of responsibilities.

## Centralized Configuration

Configuration is managed through:

- **config-service**
- external configuration repository: `commerce-platform-config-repo`

This allows each service to load its configuration from a centralized source instead of depending exclusively on local property files.

## Business Workflows

### Cart Validation Flow

When a cart is created or updated:

1. `cart-service` extracts the product IDs from the cart items
2. `product-service` validates whether those products exist
3. if one or more products do not exist, the request is rejected

### Order Creation Flow

When an order is created:

1. `order-service` validates that all requested products exist through `product-service`
2. `order-service` validates stock availability through `inventory-service`
3. the total amount is calculated internally
4. if the order status is `PAID`, inventory is discounted
5. the order is saved together with its order items

### Authentication Flow

When a user authenticates:

1. credentials are sent to `auth-service`
2. the user is authenticated through Spring Security
3. a JWT token is generated
4. the token is used to access protected routes through the gateway and secured services
5. each protected service validates the token through its authentication filter

## Error Handling

Global exception handling is implemented across services using `@RestControllerAdvice`.

Handled scenarios include:

- entity not found
- validation errors
- insufficient stock
- invalid business requests
- remote service failures with Circuit Breaker fallback
- authentication and authorization related errors

## Technologies Used

- Java 17
- Spring Boot
- Spring Cloud
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
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

## What I Practiced

Through this project, I reinforced knowledge in:

- microservices architecture with Spring Cloud
- service discovery and API Gateway patterns
- centralized configuration with Config Server
- inter-service communication with OpenFeign
- resilience patterns with Circuit Breaker
- distributed business workflows
- REST API design
- layered backend architecture
- validation and global exception handling
- authentication with Spring Security
- JWT generation and validation
- management of users, roles, and permissions
- distributed request protection using token authentication filters

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
├── auth-service/
├── user-service/
├── docs/
└── README.md
