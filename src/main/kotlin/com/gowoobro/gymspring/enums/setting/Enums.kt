package com.gowoobro.gymspring.enums.setting


enum class Type {
    STRING,  // 문자열
    NUMBER,  // 숫자
    BOOLEAN,  // 참거짓
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                STRING -> "문자열"
                NUMBER -> "숫자"
                BOOLEAN -> "참거짓"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "문자열" -> STRING
                "숫자" -> NUMBER
                "참거짓" -> BOOLEAN
                else -> null
            }
        }
    }
}

