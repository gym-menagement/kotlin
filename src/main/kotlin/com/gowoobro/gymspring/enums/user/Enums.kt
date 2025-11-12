package com.gowoobro.gymspring.enums.user


enum class Level {
    LEVEL_1,  // 일반회원
    LEVEL_2,  // 트레이너
    LEVEL_3,  // 헬스장관리자
    LEVEL_4,  // 플랫폼관리자
    LEVEL_9,  // 최고관리자
;

    companion object {
        fun getDisplayName(value: Level): String {
            return when (value) {
                LEVEL_1 -> "일반회원"
                LEVEL_2 -> "트레이너"
                LEVEL_3 -> "헬스장관리자"
                LEVEL_4 -> "플랫폼관리자"
                LEVEL_9 -> "최고관리자"
            }
        }

        fun fromString(value: String): Level? {
            return when (value) {
                "일반회원" -> LEVEL_1
                "트레이너" -> LEVEL_2
                "헬스장관리자" -> LEVEL_3
                "플랫폼관리자" -> LEVEL_4
                "최고관리자" -> LEVEL_9
                else -> null
            }
        }
    }
}


enum class Use {
    USE,  // 사용
    NOTUSE,  // 사용안함
;

    companion object {
        fun getDisplayName(value: Use): String {
            return when (value) {
                USE -> "사용"
                NOTUSE -> "사용안함"
            }
        }

        fun fromString(value: String): Use? {
            return when (value) {
                "사용" -> USE
                "사용안함" -> NOTUSE
                else -> null
            }
        }
    }
}


enum class Type {
    NORMAL,  // 일반
    KAKAO,  // 카카오
    NAVER,  // 네이버
    GOOGLE,  // 구글
    APPLE,  // 애플
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NORMAL -> "일반"
                KAKAO -> "카카오"
                NAVER -> "네이버"
                GOOGLE -> "구글"
                APPLE -> "애플"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "일반" -> NORMAL
                "카카오" -> KAKAO
                "네이버" -> NAVER
                "구글" -> GOOGLE
                "애플" -> APPLE
                else -> null
            }
        }
    }
}


enum class Role {
    MEMBER,  // 회원
    TRAINER,  // 트레이너
    STAFF,  // 직원
    GYM_ADMIN,  // 헬스장관리자
    PLATFORM_ADMIN,  // 플랫폼관리자
;

    companion object {
        fun getDisplayName(value: Role): String {
            return when (value) {
                MEMBER -> "회원"
                TRAINER -> "트레이너"
                STAFF -> "직원"
                GYM_ADMIN -> "헬스장관리자"
                PLATFORM_ADMIN -> "플랫폼관리자"
            }
        }

        fun fromString(value: String): Role? {
            return when (value) {
                "회원" -> MEMBER
                "트레이너" -> TRAINER
                "직원" -> STAFF
                "헬스장관리자" -> GYM_ADMIN
                "플랫폼관리자" -> PLATFORM_ADMIN
                else -> null
            }
        }
    }
}


enum class Sex {
    MALE,  // 남성
    FEMALE,  // 여성
;

    companion object {
        fun getDisplayName(value: Sex): String {
            return when (value) {
                MALE -> "남성"
                FEMALE -> "여성"
            }
        }

        fun fromString(value: String): Sex? {
            return when (value) {
                "남성" -> MALE
                "여성" -> FEMALE
                else -> null
            }
        }
    }
}

