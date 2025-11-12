package com.gowoobro.gymspring.enums.user


enum class Level {
    NONE,
    NORMAL,  // 일반회원
    MANAGER,  // 트레이너/직원
    ADMIN,  // 헬스장관리자
    SUPERADMIN,  // 플랫폼관리자
    ROOTADMIN,  // 최고관리자
;

    companion object {
        fun getDisplayName(value: Level): String {
            return when (value) {
                NONE -> ""
                NORMAL -> "일반회원"
                MANAGER -> "트레이너/직원"
                ADMIN -> "헬스장관리자"
                SUPERADMIN -> "플랫폼관리자"
                ROOTADMIN -> "최고관리자"
            }
        }

        fun fromString(value: String): Level? {
            return when (value) {
                "" -> NONE
                "일반회원" -> NORMAL
                "트레이너/직원" -> MANAGER
                "헬스장관리자" -> ADMIN
                "플랫폼관리자" -> SUPERADMIN
                "최고관리자" -> ROOTADMIN
                else -> null
            }
        }
    }
}


enum class Use {
    NONE,
    USE,  // 사용
    NOTUSE,  // 사용안함
;

    companion object {
        fun getDisplayName(value: Use): String {
            return when (value) {
                NONE -> ""
                USE -> "사용"
                NOTUSE -> "사용안함"
            }
        }

        fun fromString(value: String): Use? {
            return when (value) {
                "" -> NONE
                "사용" -> USE
                "사용안함" -> NOTUSE
                else -> null
            }
        }
    }
}


enum class Type {
    NONE,
    NORMAL,  // 일반
    KAKAO,  // 카카오
    NAVER,  // 네이버
    GOOGLE,  // 구글
    APPLE,  // 애플
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NONE -> ""
                NORMAL -> "일반"
                KAKAO -> "카카오"
                NAVER -> "네이버"
                GOOGLE -> "구글"
                APPLE -> "애플"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "" -> NONE
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
    NONE,
    MEMBER,  // 회원
    TRAINER,  // 트레이너
    STAFF,  // 직원
    GYM_ADMIN,  // 헬스장관리자
    PLATFORM_ADMIN,  // 플랫폼관리자
;

    companion object {
        fun getDisplayName(value: Role): String {
            return when (value) {
                NONE -> ""
                MEMBER -> "회원"
                TRAINER -> "트레이너"
                STAFF -> "직원"
                GYM_ADMIN -> "헬스장관리자"
                PLATFORM_ADMIN -> "플랫폼관리자"
            }
        }

        fun fromString(value: String): Role? {
            return when (value) {
                "" -> NONE
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
    NONE,
    MALE,  // 남성
    FEMALE,  // 여성
;

    companion object {
        fun getDisplayName(value: Sex): String {
            return when (value) {
                NONE -> ""
                MALE -> "남성"
                FEMALE -> "여성"
            }
        }

        fun fromString(value: String): Sex? {
            return when (value) {
                "" -> NONE
                "남성" -> MALE
                "여성" -> FEMALE
                else -> null
            }
        }
    }
}

