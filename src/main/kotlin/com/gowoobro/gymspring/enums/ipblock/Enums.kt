package com.gowoobro.gymspring.enums.ipblock


enum class Type {
    IPV4,  // IPv4
    IPV6,  // IPv6
    RANGE,  // 범위
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                IPV4 -> "IPv4"
                IPV6 -> "IPv6"
                RANGE -> "범위"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "IPv4" -> IPV4
                "IPv6" -> IPV6
                "범위" -> RANGE
                else -> null
            }
        }
    }
}


enum class Policy {
    ALLOW,  // 허용
    DENY,  // 거부
    BLOCK,  // 차단
;

    companion object {
        fun getDisplayName(value: Policy): String {
            return when (value) {
                ALLOW -> "허용"
                DENY -> "거부"
                BLOCK -> "차단"
            }
        }

        fun fromString(value: String): Policy? {
            return when (value) {
                "허용" -> ALLOW
                "거부" -> DENY
                "차단" -> BLOCK
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

