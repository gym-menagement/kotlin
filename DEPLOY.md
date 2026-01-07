# Gymspring 배포 가이드

## 🚀 배포 프로세스

### 1. 로컬에서 이미지 빌드 및 푸시

```bash
# 이미지 빌드 및 Docker Hub에 푸시
make push
```

### 2. 서버 설정

#### 2.1 환경 변수 파일 생성

서버에 `/data/gymspring/.env` 파일을 생성:

```bash
# /data/gymspring/.env
SPRING_PROFILES_ACTIVE=prod
TZ=Asia/Seoul

# Database - mariadb 컨테이너 사용
SPRING_DATASOURCE_URL=jdbc:mysql://mariadb:3306/gym?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8
SPRING_DATASOURCE_USERNAME=gym
SPRING_DATASOURCE_PASSWORD=gymdb
```

#### 2.2 디렉토리 생성

```bash
sudo mkdir -p /data/gymspring/config
sudo mkdir -p /data/gymspring/data
```

#### 2.3 Firebase 설정 (선택사항)

Firebase를 사용한다면:

```bash
# Firebase 서비스 계정 파일 복사
sudo cp firebase-service-account.json /data/gymspring/config/
```

### 3. 서버 docker-compose.yml 확인

서버의 메인 docker-compose.yml에 다음 내용이 있는지 확인:

```yaml
gymspring:
  image: kobums/gymspring:latest
  container_name: gymspring
  ports:
    - '8004:8004'
  volumes:
    - /data/gymspring/config:/app/config
    - /data/gymspring/data:/app/data
  env_file:
    - /data/gymspring/.env
  depends_on:
    - mariadb
  networks:
    - appnet
    - db
  restart: always
```

### 4. 배포 실행

```bash
# 서버에서 실행
cd /path/to/docker-compose
docker-compose pull gymspring
docker-compose up -d gymspring
```

### 5. 로그 확인

```bash
# 로그 확인
docker logs -f gymspring

# 또는
docker-compose logs -f gymspring
```

## 🔧 Makefile 명령어

### 로컬 개발

```bash
make run          # Spring Boot 로컬 실행
make test         # 테스트 실행
make server       # Gradle 빌드
```

### Docker 관련

```bash
make docker       # 이미지 빌드 (빌드 + dockerbuild)
make dockerbuild  # Docker 이미지만 빌드
make dockerrun    # 로컬에서 Docker 컨테이너 실행
make push         # 이미지 빌드 및 Docker Hub에 푸시
```

### Docker Compose

```bash
make compose-up    # docker-compose로 시작
make compose-down  # docker-compose 중지
make compose-logs  # 로그 확인
```

### 정리

```bash
make clean        # Gradle 정리 + Docker 컨테이너 제거
```

## 🌐 네트워크 구조

```
┌─────────────────────────────────────────┐
│            nginx (80, 443)              │
│         (리버스 프록시)                    │
└──────────────┬──────────────────────────┘
               │
        ┌──────┴──────┐
        │   appnet    │
        └──────┬──────┘
               │
    ┏━━━━━━━━━┻━━━━━━━━━┓
    ┃                    ┃
┌───┴────┐         ┌────┴─────┐
│gymspring│         │ mariadb  │
│ :8004  │◄────────┤  :3306   │
└────────┘   db    └──────────┘
             network
```

## 📝 주요 포인트

1. **데이터베이스 연결**: 서버에서는 `mariadb` 컨테이너 이름으로 연결
2. **환경 변수**: `.env` 파일로 관리
3. **볼륨**: `/data/gymspring/` 아래에 설정 및 데이터 저장
4. **네트워크**: `appnet`과 `db` 두 네트워크에 연결

## 🔍 트러블슈팅

### 데이터베이스 연결 실패

```bash
# mariadb 컨테이너 상태 확인
docker ps | grep mariadb

# gymspring 컨테이너에서 연결 테스트
docker exec -it gymspring bash
apt-get update && apt-get install -y telnet
telnet mariadb 3306
```

### 로그 확인

```bash
# 전체 로그
docker logs gymspring

# 실시간 로그
docker logs -f gymspring

# 최근 100줄
docker logs --tail 100 gymspring
```

## 🎯 빠른 배포

```bash
# 로컬에서
make push

# 서버에서
docker-compose pull gymspring && docker-compose up -d gymspring
```
