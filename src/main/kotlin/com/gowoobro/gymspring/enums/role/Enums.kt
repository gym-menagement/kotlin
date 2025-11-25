package com.gowoobro.gymspring.enums.role


enum class Roleid {
    NONE,
    MEMBER,  // 회원
    TRAINER,  // 트레이너
    STAFF,  // 직원
    GYM_ADMIN,  // 헬스장관리자
    PLATFORM_ADMIN,  // 플랫폼관리자
;

    companion object {
        fun getDisplayName(value: Roleid): String {
            return when (value) {
                NONE -> ""
                MEMBER -> "회원"
                TRAINER -> "트레이너"
                STAFF -> "직원"
                GYM_ADMIN -> "헬스장관리자"
                PLATFORM_ADMIN -> "플랫폼관리자"
            }
        }

        fun fromString(value: String): Roleid? {
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

