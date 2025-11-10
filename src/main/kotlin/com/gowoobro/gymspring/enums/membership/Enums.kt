package com.gowoobro.gymspring.enums.membership


enum class Sex {
    0,  // 남성
    1,  // 여성
;

    companion object {
        fun getDisplayName(value: Sex): String {
            return when (value) {
                0 -> "남성"
                1 -> "여성"
            }
        }

        fun fromString(value: String): Sex? {
            return when (value) {
                "남성" -> 0
                "여성" -> 1
                else -> null
            }
        }
    }
}

