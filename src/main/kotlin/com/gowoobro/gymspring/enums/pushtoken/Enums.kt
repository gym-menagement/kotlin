package com.gowoobro.gymspring.enums.pushtoken


enum class Isactive {
    NONE,
    INACTIVE,  // 비활성
    ACTIVE,  // 활성
;

    companion object {
        fun getDisplayName(value: Isactive): String {
            return when (value) {
                NONE -> ""
                INACTIVE -> "비활성"
                ACTIVE -> "활성"
            }
        }

        fun fromString(value: String): Isactive? {
            return when (value) {
                "" -> NONE
                "비활성" -> INACTIVE
                "활성" -> ACTIVE
                else -> null
            }
        }
    }
}

