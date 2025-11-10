package com.gowoobro.gymspring.enums.setting


enum class Type {
    NUMBERTYPE,  // 숫자
    STRINGTYPE,  // 문자
    SELECTTYPE,  // 선택
    WEEKTYPE,  // 요일
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NUMBERTYPE -> "숫자"
                STRINGTYPE -> "문자"
                SELECTTYPE -> "선택"
                WEEKTYPE -> "요일"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "숫자" -> NUMBERTYPE
                "문자" -> STRINGTYPE
                "선택" -> SELECTTYPE
                "요일" -> WEEKTYPE
                else -> null
            }
        }
    }
}

