# 헬스장 관리 플랫폼 프로젝트 설명서

## 1. 프로젝트 개요

### 프로젝트 목적
다중 헬스장을 하나의 플랫폼에서 통합 관리하며, 회원들은 하나의 앱으로 모든 헬스장을 이용할 수 있는 B2B2C 플랫폼입니다.

### 기술 스택
- **언어**: Kotlin 1.9.25
- **프레임워크**: Spring Boot 3.5.5
- **데이터베이스**: MySQL
- **인증**: JWT (jjwt 0.12.3)
- **ORM**: Spring Data JPA
- **보안**: Spring Security
- **서버 포트**: 8004

### 주요 특징
- 멀티 테넌시 구조 (다중 헬스장 독립 운영)
- JWT 기반 인증/인가 시스템
- QR 코드 기반 출입 관리
- 실시간 알림 시스템
- 계층형 아키텍처 (Controller → Service → Repository → Entity)

---

## 2. 시스템 아키텍처

### 레이어 구조
```
Controller (REST API)
    ↓
Service (비즈니스 로직)
    ↓
Repository (데이터 접근)
    ↓
Entity (JPA 엔티티)
```

### 주요 도메인 (24+ 엔티티)

**회원 관리**
- `User`: 사용자 계정 (플랫폼 관리자, 헬스장 관리자, 트레이너, 회원)
- `Role`: 역할 기반 권한 관리
- `LoginLog`: 로그인 이력 추적
- `IpBlock`: IP 기반 보안 정책

**회원권 관리**
- `Membership`: 회원권 정보 (기간제/횟수제)
- `Order`: 주문 내역
- `Payment`: 결제 정보
- `Discount`: 할인 정책
- `Term`: 기간 설정 (1/3/6/12개월)

**운동 서비스**
- `Health`: 운동 상품 (헬스/PT/요가/필라테스)
- `Healthcategory`: 운동 카테고리
- `UseHealth`: 운동 이용 기록
- `Attendance`: 출입 기록
- `MembershipUsage`: 회원권 사용 이력

**PT 관리**
- `TrainerMember`: 트레이너-회원 매칭
- `PtReservation`: PT 예약 관리
- `MemberBody`: 회원 신체 측정 기록 (체중, 체지방, 근육량 등)

**락커 관리**
- `Rocker`: 락커 정보
- `RockerUsage`: 락커 사용 이력
- `Rockergroup`: 락커 그룹

**시스템 관리**
- `Gym`: 헬스장 정보
- `Setting`: 시스템 설정
- `Alarm`: 알림 관리
- `Notice`: 공지사항
- `Settlement`: 정산 관리

### 인증 & 보안

**JWT 인증 플로우**
```
로그인 요청
    ↓
CustomUserDetailsService (DB에서 사용자 조회)
    ↓
비밀번호 검증 (BCrypt)
    ↓
JWT 토큰 발급 (24시간 유효)
    ↓
이후 요청 시 JwtAuthenticationFilter에서 토큰 검증
```

**보안 설정**
- Public 엔드포인트: `/api/jwt`, `/api/auth/**`
- 인증 필요: 그 외 모든 `/api/**` 엔드포인트
- BCrypt 암호화로 비밀번호 저장
- IP 기반 접근 제어 (IpBlock)

---

## 3. 핵심 비즈니스 프로세스

### 회원 가입 → 회원권 구매 → 출입
```
1. 회원가입 (User 생성, ROLE: MEMBER)
2. 헬스장 검색 (Gym 조회)
3. 운동권 선택 (Health, Term 조회)
4. 주문 생성 (Order)
5. 결제 (Payment)
6. 회원권 발급 (Membership)
7. QR 코드 자동 생성 (MemberQR)
8. 출입 시 QR 스캔
9. 출입 기록 저장 (Attendance)
10. 사용 이력 업데이트 (MembershipUsage)
```

### PT 이용 프로세스
```
1. 트레이너 배정 (TrainerMember)
2. PT 예약 (PtReservation: 날짜, 시간 선택)
3. 트레이너 승인
4. 예약 확정 알림 (Alarm + Push)
5. PT 진행
6. 신체 측정 기록 (MemberBody)
7. 진행도 그래프 확인
```

### 정산 프로세스
```
1. 일일 자정 배치 작업
2. 당일 매출 집계 (Settlement)
   - 헬스장별 매출
   - 결제 수단별 금액
   - 취소/환불 금액
3. 월말 정산
   - 플랫폼 수수료 계산
   - 헬스장별 정산 금액 확정
```

---

## 4. REST API 설계

### 표준 API 패턴 (모든 엔티티 공통)
```
GET    /api/{entity}                  - 페이징 목록 조회 (page=0, pageSize=10)
GET    /api/{entity}/{id}             - 단일 조회
GET    /api/{entity}/search/{field}   - 특정 필드 검색
GET    /api/{entity}/count            - 전체 개수
POST   /api/{entity}                  - 단일 생성
POST   /api/{entity}/batch            - 일괄 생성
PUT    /api/{entity}/{id}             - 수정
DELETE /api/{entity}/{id}             - 삭제
DELETE /api/{entity}/batch            - 일괄 삭제
```

### API 응답 형식
```kotlin
// 목록 응답
ApiResponse<T>(
    _t: LocalDateTime,  // 타임스탬프
    code: Int,          // 상태 코드
    items: List<T>      // 데이터 목록
)

// 단일 응답
ApiSingleResponse<T>(
    _t: LocalDateTime,
    code: Int,
    item: T             // 단일 데이터
)
```

### 로그인 API
```
GET  /api/jwt?loginid={id}&passwd={pw}
POST /api/auth/login
```

---

## 5. 데이터베이스 설계

### 네이밍 컨벤션
- 테이블: `{entity}_tb` (예: `user_tb`, `membership_tb`)
- 컬럼: `{prefix}_{field}` (예: `u_id`, `u_loginid`, `u_passwd`)
- 프리픽스: 엔티티명 축약 (user → u_, membership → m_)

### 주요 관계
```
User (1) ─── (N) Membership : 회원이 여러 회원권 보유
Membership (1) ─── (N) MembershipUsage : 회원권 사용 이력
Gym (1) ─── (N) Health : 헬스장이 여러 운동 상품 보유
User (1) ─── (N) Order : 회원의 주문 이력
Order (1) ─── (1) Payment : 주문-결제 1:1 매칭
Trainer (1) ─── (N) TrainerMember : 트레이너가 여러 회원 담당
```

### Enum 정의 ([Enums.kt](src/main/kotlin/com/gowoobro/gymspring/entity/Enums.kt))
```kotlin
Type: SYSTEM, USER, ADMIN, NOTIFICATION, WARNING, ERROR
Status: ACTIVE, INACTIVE, PENDING, COMPLETED, CANCELLED
Policy: ALLOW, DENY, BLOCK, WARN
Use: YES, NO, ENABLED, DISABLED
Level: BEGINNER, INTERMEDIATE, ADVANCED, PREMIUM, VIP
UserRole: ADMIN, MANAGER, TRAINER, MEMBER, GUEST
```

---

## 6. 주요 구현 포인트

### 1) Spring Security + JWT 통합
- [SecurityConfig.kt](src/main/kotlin/com/gowoobro/gymspring/config/SecurityConfig.kt)에서 보안 정책 설정
- `JwtAuthenticationFilter`가 모든 요청 가로채서 JWT 검증
- `CustomUserDetailsService`로 DB 기반 사용자 인증

### 2) Kotlin + JPA 설정
- `allOpen` 플러그인으로 JPA 엔티티 자동 open (Kotlin class는 기본 final)
- JSR305 strict mode로 null safety 강화

### 3) 트랜잭션 관리
- 모든 Service 클래스에 `@Transactional` 적용
- 비즈니스 로직 단위로 원자성 보장

### 4) 페이징 & 검색
- Spring Data JPA의 `Pageable` 활용
- Repository에서 쿼리 메서드 네이밍 컨벤션 사용
  ```kotlin
  fun findByLoginid(loginid: String): User?
  fun findByName(name: String): List<User>
  ```

### 5) 배치 작업
- 일일 정산 배치 (자정 실행)
- 회원권 만료 알림 (D-7, D-3, D-1)
- 락커 만료 알림

---

## 7. 빌드 & 실행

```bash
# 빌드
./gradlew build

# 실행
./gradlew bootRun

# 테스트
./gradlew test

# Kotlin 컴파일만
./gradlew compileKotlin
```

### 설정 파일
- [application.yml](src/main/resources/application.yml): DB 연결, JWT 설정, 로깅 설정
- JWT 만료 시간: 24시간 (86400000ms)
- Hibernate DDL: update (스키마 자동 업데이트)

---

## 8. 면접 예상 질문 & 답변

### Q1. 왜 Kotlin을 선택했나요?
**A**: Kotlin은 Java와 100% 호환되면서도 null safety, data class, extension function 등으로 코드 간결성과 안정성을 크게 향상시킵니다. 특히 JPA 엔티티 작성 시 보일러플레이트가 줄어들고, Spring Boot와의 통합도 매우 우수합니다.

### Q2. JWT를 사용한 이유는?
**A**: 세션 기반 인증은 서버에 상태를 저장하므로 확장성에 제약이 있습니다. JWT는 stateless하여 수평 확장이 용이하고, 모바일 앱과의 통신에도 적합합니다. 또한 토큰에 사용자 정보를 담아 매번 DB 조회를 줄일 수 있습니다.

### Q3. 멀티 테넌시는 어떻게 구현했나요?
**A**: 각 엔티티에 `gym_id` 외래키를 포함시켜 데이터를 격리했습니다. 헬스장 관리자는 본인의 `gym_id`에 해당하는 데이터만 조회/수정할 수 있도록 Service 레이어에서 필터링합니다. 플랫폼 관리자만 전체 데이터에 접근 가능합니다.

### Q4. 성능 최적화는 어떻게 했나요?
**A**:
- 페이징으로 대량 데이터 조회 시 메모리 부담 감소
- Repository에서 필요한 필드만 조회하는 쿼리 메서드 정의
- `@Transactional(readOnly = true)`로 읽기 전용 트랜잭션 최적화
- 인덱스 설정 (loginid, phone 등 자주 조회되는 컬럼)

### Q5. 보안은 어떻게 처리했나요?
**A**:
- BCrypt로 비밀번호 암호화 저장
- JWT 토큰으로 인증/인가
- IP 기반 접근 제어 (IpBlock)
- Spring Security의 CORS, CSRF 설정
- 로그인 이력 추적 (LoginLog)

### Q6. 확장 가능성은?
**A**:
- RESTful API 설계로 프론트엔드 독립성 확보 (웹/앱 모두 사용 가능)
- JWT로 stateless 구조 → 서버 수평 확장 용이
- 도메인별로 엔티티/서비스 분리 → 마이크로서비스 전환 가능
- Repository 패턴으로 DB 변경 시 영향 최소화

### Q7. 가장 어려웠던 부분은?
**A**: QR 코드 기반 출입 인증 시스템 구현이었습니다. 회원권 유효성 검증(만료일, 잔여 횟수, 시간대), 중복 출입 방지, 실시간 알림 연동 등을 동시에 처리해야 했습니다. 트랜잭션과 동시성 제어를 통해 해결했습니다.

### Q8. 개선하고 싶은 부분은?
**A**:
- Redis 캐싱 도입 (자주 조회되는 헬스장 정보, 회원권 정보)
- ElasticSearch 도입 (헬스장 검색 성능 향상)
- 이벤트 기반 아키텍처 (결제 완료 → 회원권 발급 → 알림 전송)
- Kubernetes로 컨테이너 오케스트레이션

---

## 9. 프로젝트 강점

1. **확장 가능한 구조**: 멀티 테넌시로 무한대 헬스장 추가 가능
2. **표준화된 API**: 모든 엔티티가 동일한 패턴의 API 제공
3. **완전한 비즈니스 로직**: 회원 가입부터 정산까지 전 과정 구현
4. **모바일 친화적**: JWT + REST API로 앱 통신 최적화
5. **보안 강화**: 다층 보안 (JWT + IP 차단 + 로그 추적)
6. **Kotlin 활용**: 현대적인 언어로 안정성과 생산성 향상

---

## 10. 핵심 파일 참고

- [SecurityConfig.kt](src/main/kotlin/com/gowoobro/gymspring/config/SecurityConfig.kt): 보안 설정
- [Enums.kt](src/main/kotlin/com/gowoobro/gymspring/entity/Enums.kt): 공통 Enum 정의
- [application.yml](src/main/resources/application.yml): 애플리케이션 설정
- [CLAUDE.md](CLAUDE.md): 상세 프로젝트 문서

---

**면접 팁**:
- 기술 스택을 단순 나열하지 말고, "왜 선택했는지" 설명할 것
- 어려웠던 점과 해결 방법을 구체적으로 준비할 것
- 개선점을 제시하며 성장 가능성을 보여줄 것
- 비즈니스 도메인(헬스장 운영)에 대한 이해도를 강조할 것

**행운을 빕니다!**
