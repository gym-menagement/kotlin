package com.gowoobro.gymspring.enums.token


enum class Status {
    ACTIVE,  // 활성
    EXPIRED,  // 만료
    REVOKED,  // 폐기
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                ACTIVE -> "활성"
                EXPIRED -> "만료"
                REVOKED -> "폐기"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "활성" -> ACTIVE
                "만료" -> EXPIRED
                "폐기" -> REVOKED
                else -> null
            }
        }
    }
}

