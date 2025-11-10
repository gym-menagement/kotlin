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

---

## Business Process & Platform Flow

This is a **multi-gym platform** where multiple gyms operate independently on a single system, and members can access all gyms through one unified app.

### 1. Platform Admin

**Initial Setup:**
```
Platform setup → Create system admin account (user_tb, role: ADMIN)
→ Configure basic settings (discount policies, app version management)
→ Set IP security policies (ipblock_tb)
```

**Gym Onboarding:**
```
Gym business inquiry → Contract signing
→ Register gym information (gym_tb)
→ Create gym admin account (user_tb, role: GYM_ADMIN)
→ Provide initial data setup support
```

**Operations Management:**
- Monitor all gyms (revenue, member count, usage status)
- Settlement management (settlement_tb) - fee calculation per gym
- Send system-wide notices (notice_tb, target: all)
- App version control (appversion_tb) - force update control
- Log analysis (loginlog_tb) and security management

### 2. Gym Owner/Manager

**Gym Registration & Setup:**
```
Receive account from platform admin
→ Enter gym basic info (gym_tb: name, address, contact)
→ Set operating hours/days (daytype_tb: weekday/weekend/holiday)
→ Configure time-slot pricing (timeslot_tb)
```

**Product Setup:**
```
Create exercise categories (healthcategory_tb: gym/PT/yoga/pilates)
→ Register products per category (health_tb)
   - Set periods (term_tb: 1/3/6/12 months)
   - Set count-based passes (e.g., 10/20 sessions)
   - Set prices and discounts (discount_tb)
→ Register locker info (rocker_tb: number, location, type)
```

**Staff Management:**
```
Create trainer accounts (user_tb, role: TRAINER)
→ Set staff permissions (role: STAFF)
→ Configure PT schedule available time slots
```

**Daily Operations:**
- Member management: new registration, membership renewal, refunds
- Check entry records (attendance_tb)
- Locker assignment and management (rockerusage_tb)
- Respond to inquiries (inquiry_tb)
- Post announcements (notice_tb, target: own gym members)
- Check revenue (order_tb, payment_tb)
- View settlement details (settlement_tb)

### 3. Regular Member

**Sign Up:**
```
Download app → Sign up (user_tb, role: MEMBER)
→ Enter basic info (name, phone, birth date)
→ Login (loginlog_tb recorded)
→ Register push notification token (pushtoken_tb)
```

**Find Gym & Purchase Membership:**
```
Search gym by location/keyword (gym_tb)
→ View gym details (hours, facilities, products)
→ Select exercise pass (health_tb)
   - Choose category (gym/PT/yoga)
   - Choose period/count
   - Check discount
→ Create order (order_tb)
→ Payment (payment_tb)
→ Issue membership (membership_tb)
→ Auto-generate QR code (memberqr_tb)
```

**Check-in (Core Feature):**
```
Arrive at gym → Open app → Display QR code (memberqr_tb)
→ Scan QR at entry terminal
→ Authenticate member and verify membership validity
   ✓ Check expiration date
   ✓ Check remaining sessions (if count-based)
   ✓ Check available time slot (timeslot_tb)
→ Save entry record (attendance_tb)
→ Update usage history (membershipusage_tb)
   - Count-based: remaining sessions -1
   - Period-based: record only
```

**PT Usage:**
```
Assign trainer (trainermember_tb)
→ Book PT session (ptreservation_tb: select date, time)
→ Trainer approval
→ Booking confirmed notification (alarm_tb, push)
→ PT session conducted
→ After completion, measure body metrics (memberbody_tb)
   - Weight, body fat %, muscle mass, etc.
   - View progress graphs
```

**Locker Usage:**
```
Request locker → Check available lockers (rocker_tb, status: AVAILABLE)
→ Assign locker (rockerusage_tb)
→ Pay deposit/monthly fee
→ Manage usage period (start date ~ end date)
→ Notification before expiration (alarm_tb)
```

**Other Services:**
- View workout records (attendance_tb, membershipusage_tb)
- Check payment history (order_tb, payment_tb)
- Track body changes (memberbody_tb)
- View announcements (notice_tb)
- Submit inquiries (inquiry_tb)
- Reissue QR code (if lost)

### 4. Trainer

**Account & Permissions:**
```
Gym admin creates account (user_tb, role: TRAINER)
→ Login → Auto-mapped to assigned gym
```

**Member Management:**
```
Get assigned members (trainermember_tb)
→ Manage PT schedule (ptreservation_tb)
   - Set available time slots
   - Approve/reject member booking requests
   - Modify/cancel schedule
→ Input member body metrics (memberbody_tb)
→ Manage workout programs and provide feedback
```

**Schedule Management:**
- Check daily PT schedule
- Handle no-show members (pr_status: 3)
- Mark session completion (pr_status: 1)
- Check progress per member

### 5. Payment & Settlement Process

**Membership Purchase Payment:**
```
Member selects product → Create order (order_tb)
→ Select payment method (card/transfer/cash)
→ Process payment (payment_tb)
   - Payment status: PENDING → SUCCESS/FAIL
→ On success: activate membership (membership_tb)
→ On failure: notify and guide retry
```

**Monthly/Daily Settlement:**
```
Daily batch job at midnight
→ Aggregate daily revenue (settlement_tb)
   - Revenue per gym
   - Amount per payment method
   - Cancellation/refund amounts
→ Month-end settlement
   - Calculate platform fees
   - Confirm settlement amount per gym
   - Generate settlement report
```

### 6. Notification System

**Push Notifications:**
```
Trigger event (e.g., PT booking confirmed, membership expiring soon)
→ Create notification (alarm_tb)
→ Query target member's push token (pushtoken_tb)
→ Send push via FCM/APNS
→ Record delivery result
```

**Notification Types:**
- Membership expiration D-7, D-3, D-1
- PT booking confirmed/cancelled
- Locker expiring soon
- Payment success/failure
- Announcements posted (important notices)
- New events/promotions

### 7. Security & Monitoring

**Login Security:**
```
Login attempt → Check IP address (ipblock_tb)
→ Deny access if blocked IP
→ On normal login, record (loginlog_tb)
   - IP, time, device info
→ Alert on abnormal access pattern detection
```

**Access Control:**
- Platform admin: full data access
- Gym admin: own gym data only
- Trainer: assigned member data only
- Member: own data only

### 8. App Version Management

```
Release new version → Register version info (appversion_tb)
→ Set force update flag
→ Check version on app launch
→ If outdated:
   - Force update: redirect to store
   - Optional update: notify and allow usage
```

### Data Flow Summary

```
Member sign up (user_tb)
    ↓
Purchase gym membership (membership_tb, order_tb, payment_tb)
    ↓
Issue QR code (memberqr_tb)
    ↓
Scan at entry (attendance_tb, membershipusage_tb)
    ↓
Use services (PT, locker, etc.)
    ↓
Settlement (settlement_tb)
```

This architecture allows **multiple gyms to operate independently** on one platform, while members can access **all gyms through a single app**!
