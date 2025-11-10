package com.gowoobro.gymspring.enums.ipblock


enum class Type {
    ADMIN,  // 관리자 접근
    NORMAL,  // 일반 접근
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                ADMIN -> "관리자 접근"
                NORMAL -> "일반 접근"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "관리자 접근" -> ADMIN
                "일반 접근" -> NORMAL
                else -> null
            }
        }
    }
}


enum class Policy {
    GRANT,  // 허용
    DENY,  // 거부
;

    companion object {
        fun getDisplayName(value: Policy): String {
            return when (value) {
                GRANT -> "허용"
                DENY -> "거부"
            }
        }

        fun fromString(value: String): Policy? {
            return when (value) {
                "허용" -> GRANT
                "거부" -> DENY
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

