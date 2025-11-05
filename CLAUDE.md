# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a gym management Spring Boot application built with Kotlin. It provides REST APIs for managing gym members, memberships, payments, health services, lockers, and administrative functions. The application uses JWT-based authentication and MySQL for data persistence.

**Stack:** Spring Boot 3.5.5, Kotlin 1.9.25, Spring Data JPA, Spring Security, JWT (jjwt 0.12.3), MySQL

**Server Port:** 9410

## Build & Run Commands

```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run tests
./gradlew test

# Clean build
./gradlew clean build

# Compile Kotlin code only
./gradlew compileKotlin
```

## Architecture

### Layer Structure

The application follows a standard Spring Boot layered architecture:

```
controller → service → repository → entity
```

Each domain entity has a corresponding set of files:
- **Entity**: JPA entity with `@Entity` annotation (in `entity/`)
- **Repository**: Spring Data JPA repository interface extending `JpaRepository` (in `repository/`)
- **Service**: Business logic with `@Service` and `@Transactional` annotations (in `service/`)
- **Controller**: REST endpoints with `@RestController` (in `controller/`)

### Key Components

**Authentication & Security:**
- JWT-based authentication configured in [SecurityConfig.kt](src/main/kotlin/com/gowoobro/gymspring/config/SecurityConfig.kt)
- `JwtAuthenticationFilter` intercepts requests and validates JWT tokens from the `Authorization: Bearer <token>` header
- `CustomUserDetailsService` loads users from the database for authentication
- Public endpoints: `/api/jwt`, `/api/auth/**`, `/api/user/encode-passwords`
- All other `/api/**` endpoints require authentication

**JWT Configuration:**
- Secret key and expiration defined in [application.yml](src/main/resources/application.yml)
- Expiration: 24 hours (86400000 ms)
- Login endpoints: `GET /api/jwt?loginid=X&passwd=Y` or `POST /api/auth/login`

**API Response Format:**
- Standard responses use `ApiResponse<T>` wrapper with `_t` (timestamp), `code`, and `items` fields
- Single item responses use `ApiSingleResponse<T>` with `item` field instead of `items`

**Database Configuration:**
- MySQL database connection configured in [application.yml](src/main/resources/application.yml)
- Hibernate DDL mode: `update` (auto-updates schema)
- SQL logging enabled with formatted output

### Domain Entities

The application manages 24+ domain entities including:
- **User Management**: User, Role, LoginLog, IpBlock
- **Membership**: Membership, Order, Payment, PaymentType, PaymentForm, Discount
- **Health Services**: Health, Healthcategory, UseHealth, DayType
- **Facilities**: Rocker, Rockergroup, Gym
- **System**: Setting, SystemLog, Alarm, Term, Token, Stop

**Enums** are centralized in [Enums.kt](src/main/kotlin/com/gowoobro/gymspring/entity/Enums.kt):
- `Type`: SYSTEM, USER, ADMIN, NOTIFICATION, WARNING, ERROR, INFO, DEBUG
- `Status`: ACTIVE, INACTIVE, PENDING, COMPLETED, CANCELLED, EXPIRED
- `Policy`: ALLOW, DENY, BLOCK, WARN
- `Use`: YES, NO, ENABLED, DISABLED
- `Level`: BEGINNER, INTERMEDIATE, ADVANCED, PREMIUM, VIP
- `UserRole`: ADMIN, MANAGER, TRAINER, MEMBER, GUEST

### Controller Patterns

All controllers follow a consistent REST pattern with these endpoints:
- `GET /api/{entity}` - Get paginated list (default: page=0, pageSize=10)
- `GET /api/{entity}/{id}` - Get single entity by ID
- `GET /api/{entity}/search/{field}?{field}=value` - Search by specific field
- `GET /api/{entity}/count` - Get total count
- `POST /api/{entity}` - Create single entity
- `POST /api/{entity}/batch` - Create multiple entities
- `PUT /api/{entity}/{id}` - Update entity
- `DELETE /api/{entity}/{id}` - Delete entity
- `DELETE /api/{entity}/batch` - Delete multiple entities

### Repository Patterns

All repositories extend `JpaRepository<Entity, Long>` and override methods to provide custom query methods by entity fields. They use Spring Data JPA's method naming conventions (e.g., `findByLoginid`, `findByName`).

### Service Patterns

Services are annotated with `@Service` and `@Transactional`, containing business logic for:
- CRUD operations (create, findById, findAll with pagination, update, delete)
- Field-based search methods delegating to repository
- Batch operations (createBatch, deleteBatch)

## Development Notes

**Adding New Entities:**
1. Create entity class in `entity/` with JPA annotations
2. Create corresponding CreateRequest and UpdateRequest data classes
3. Create repository interface extending `JpaRepository` in `repository/`
4. Add custom query methods to repository using Spring Data naming
5. Create service class in `service/` with business logic
6. Create controller in `controller/` following standard REST patterns

**Password Encoding:**
- Uses BCrypt for password hashing
- CustomUserDetailsService handles authentication against encoded passwords

**Kotlin-Specific Configuration:**
- `allOpen` plugin configured for JPA entities (`@Entity`, `@MappedSuperclass`, `@Embeddable`)
- JSR305 strict mode enabled for null safety

**Database Column Naming:**
- Table columns typically prefixed with abbreviated entity name (e.g., `u_` for user_tb)
- Example: User entity has `u_id`, `u_loginid`, `u_passwd`, etc.
