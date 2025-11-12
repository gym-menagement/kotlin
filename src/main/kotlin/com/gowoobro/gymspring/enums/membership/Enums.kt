package com.gowoobro.gymspring.enums.membership


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

