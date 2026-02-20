# 🏋️ Gym Management Platform

> 다중 헬스장을 하나의 플랫폼에서 통합 관리하는 B2B2C SaaS 백엔드

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.25-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)](https://www.mysql.com)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker&logoColor=white)](https://www.docker.com)
[![License](https://img.shields.io/badge/License-Private-red)]()

---

## 📋 목차

- [프로젝트 소개](#-프로젝트-소개)
- [주요 기능](#-주요-기능)
- [기술 스택](#-기술-스택)
- [시스템 아키텍처](#-시스템-아키텍처)
- [프로젝트 구조](#-프로젝트-구조)
- [시작하기](#-시작하기)
- [API 명세](#-api-명세)
- [데이터베이스](#-데이터베이스)
- [배포](#-배포)
- [문서](#-문서)

---

## 🎯 프로젝트 소개

**Gym Management Platform**은 여러 헬스장을 하나의 플랫폼에서 통합 관리하며, 회원들이 단일 앱으로 모든 제휴 헬스장을 이용할 수 있는 **B2B2C 플랫폼**입니다.

### 핵심 가치

| 대상 | 가치 |
|------|------|
| 🏢 **플랫폼 관리자** | 전체 헬스장 통합 관리 및 정산 |
| 🏋️ **헬스장 관리자** | 회원, 회원권, 트레이너, 락커 등 독립적 운영 |
| 💪 **트레이너** | PT 예약 관리 및 회원 신체 기록 추적 |
| 👤 **회원** | 하나의 앱으로 모든 헬스장 이용 및 QR 출입 |

---

## ✨ 주요 기능

### 🔐 인증 & 보안
- JWT 기반 인증/인가 (24시간 유효)
- BCrypt 비밀번호 암호화
- IP 기반 접근 제어 (IpBlock)
- 역할 기반 권한 관리 (Admin / Manager / Trainer / Member / Guest)
- 로그인 이력 추적

### 🏠 멀티 테넌시
- 다중 헬스장 독립 운영
- 헬스장별 데이터 격리
- 플랫폼 관리자의 전체 데이터 접근

### 🎫 회원권 관리
- 기간제 / 횟수제 회원권 지원
- 주문 → 결제 → 회원권 발급 파이프라인
- 할인 정책 관리
- 회원권 사용 이력 추적

### 📱 QR 코드 출입
- 회원권 구매 시 QR 코드 자동 생성
- QR 스캔 기반 출입 인증
- 출입 기록 자동 저장
- 회원권 유효성 실시간 검증

### 🏃 PT 관리
- 트레이너-회원 매칭
- PT 예약/승인 시스템
- 회원 신체 측정 기록 (체중, 체지방, 근육량 등)
- 진행도 추적

### 🔔 알림 & 기타
- Firebase 푸시 알림 연동
- 회원권 만료 알림 (D-7, D-3, D-1)
- 락커 관리 시스템
- 공지사항 관리
- 일일/월별 정산 시스템

---

## 🛠 기술 스택

| 분류 | 기술 |
|------|------|
| **Language** | Kotlin 1.9.25 |
| **Framework** | Spring Boot 3.5.5 |
| **Security** | Spring Security + JWT (jjwt 0.12.3) |
| **ORM** | Spring Data JPA + Hibernate |
| **Database** | MySQL 8.0 |
| **Push** | Firebase Admin SDK 9.4.1 |
| **Build** | Gradle (Kotlin DSL) |
| **Java** | JDK 17 |
| **Container** | Docker + Docker Compose |

---

## 🏗 시스템 아키텍처

### 레이어 구조

```
Client (Web / Mobile App)
         │
         ▼
┌─────────────────────────┐
│   Controller (REST API) │  ← 요청 수신 및 응답 반환
├─────────────────────────┤
│   Service (비즈니스 로직)  │  ← 핵심 로직 처리 + @Transactional
├─────────────────────────┤
│   Repository (데이터 접근) │  ← Spring Data JPA
├─────────────────────────┤
│   Entity (JPA 엔티티)     │  ← DB 테이블 매핑
└─────────────────────────┘
         │
         ▼
      MySQL DB
```

### 인증 플로우

```
로그인 요청 → CustomUserDetailsService (DB 조회) → BCrypt 검증 → JWT 발급 (24h)
                                                                      │
이후 API 요청 → JwtAuthenticationFilter → JWT 검증 → SecurityContext 설정 → API 처리
```

---

## 📁 프로젝트 구조

```
src/main/kotlin/com/gowoobro/gymspring/
├── GymspringApplication.kt       # 메인 애플리케이션
├── config/                        # Spring 설정 (Security, JWT 등)
├── controller/                    # REST API 컨트롤러 (39개)
├── entity/                        # JPA 엔티티 (39개)
├── enums/                         # Enum 정의 (23개)
├── repository/                    # JPA Repository (37개)
└── service/                       # 비즈니스 로직 (39개)
```

### 주요 도메인 (24+ 엔티티)

| 도메인 | 엔티티 | 설명 |
|--------|--------|------|
| **회원** | User, Role, LoginLog, IpBlock | 사용자 계정 및 권한 |
| **회원권** | Membership, Order, Payment, Discount, Term | 구매 및 결제 |
| **운동** | Health, Healthcategory, UseHealth, Attendance | 운동 이용 |
| **PT** | TrainerMember, PtReservation, MemberBody | PT 관리 |
| **락커** | Rocker, RockerUsage, Rockergroup | 락커 관리 |
| **시스템** | Gym, Setting, Alarm, Notice, Settlement | 시스템 운영 |

---

## 🚀 시작하기

### 사전 요구사항

- **JDK 17** 이상
- **MySQL 8.0** 이상
- **Docker** (선택사항)

### 로컬 실행

```bash
# 1. 저장소 클론
git clone https://github.com/gowoobro/gym_management.git
cd gym_management/kotlin

# 2. 데이터베이스 설정
mysql -u root -p < gym.sql
mysql -u root -p gym < insert_sample_data.sql

# 3. 애플리케이션 실행
./gradlew bootRun
```

### Docker로 실행

```bash
# Docker Compose로 실행
make compose-up

# 또는 개별 실행
make docker-build
make docker-run
```

> 서버 포트: **8004**

---

## 📡 API 명세

### 표준 CRUD 패턴

모든 엔티티는 동일한 RESTful API 패턴을 따릅니다:

| Method | Endpoint | 설명 |
|--------|----------|------|
| `GET` | `/api/{entity}` | 페이징 목록 조회 |
| `GET` | `/api/{entity}/{id}` | 단일 조회 |
| `GET` | `/api/{entity}/search/{field}` | 필드 검색 |
| `GET` | `/api/{entity}/count` | 전체 개수 |
| `POST` | `/api/{entity}` | 단일 생성 |
| `POST` | `/api/{entity}/batch` | 일괄 생성 |
| `PUT` | `/api/{entity}/{id}` | 수정 |
| `DELETE` | `/api/{entity}/{id}` | 삭제 |
| `DELETE` | `/api/{entity}/batch` | 일괄 삭제 |

### 인증 API

```
GET  /api/jwt?loginid={id}&passwd={pw}   # JWT 토큰 발급
POST /api/auth/login                      # 로그인
```

### 응답 형식

```json
{
  "_t": "2026-02-20T12:00:00",
  "code": 200,
  "items": [ ... ]
}
```

> 📮 Postman Collection 파일: `Gym_API.postman_collection.json`

---

## 🗄 데이터베이스

### 네이밍 컨벤션

- 테이블: `{entity}_tb` (예: `user_tb`, `membership_tb`)
- 컬럼: `{prefix}_{field}` (예: `u_id`, `u_loginid`, `u_passwd`)

### 주요 관계

```
User (1) ─── (N) Membership     회원이 여러 회원권 보유
Membership (1) ─── (N) Usage    회원권 사용 이력
Gym (1) ─── (N) Health          헬스장이 여러 운동 상품 보유
User (1) ─── (N) Order          회원의 주문 이력
Order (1) ─── (1) Payment       주문-결제 1:1 매칭
Trainer (1) ─── (N) Member      트레이너가 여러 회원 담당
```

### 스키마 파일

- `gym.sql` — 전체 테이블 생성 스크립트
- `insert_sample_data.sql` — 샘플 데이터

---

## 🐳 배포

### 빠른 배포

```bash
# 로컬에서 Docker Hub로 푸시
make push

# 서버에서 배포
docker-compose pull gymspring && docker-compose up -d gymspring
```

### 네트워크 구조

```
nginx (80/443)  ← 리버스 프록시
     │
  appnet
     │
gymspring (:8004) ←── mariadb (:3306)
                        db network
```

> 자세한 배포 방법은 [DEPLOY.md](DEPLOY.md) 및 [DOCKER.md](DOCKER.md)를 참고하세요.

---

## 📚 문서

| 문서 | 설명 |
|------|------|
| [EXPLAIN.md](EXPLAIN.md) | 프로젝트 상세 설명서 (아키텍처, 비즈니스 프로세스, 면접 Q&A) |
| [DEPLOY.md](DEPLOY.md) | 서버 배포 가이드 |
| [DOCKER.md](DOCKER.md) | Docker 상세 사용 가이드 |
| [CLAUDE.md](CLAUDE.md) | AI 어시스턴트용 프로젝트 문서 |

---

<p align="center">
  <b>Made with ❤️ by gowoobro</b>
</p>
