package com.gowoobro.gymspring.enums.memberqr


enum class Isactive {
    INACTIVE,  // 비활성
    ACTIVE,  // 활성
;

    companion object {
        fun getDisplayName(value: Isactive): String {
            return when (value) {
                INACTIVE -> "비활성"
                ACTIVE -> "활성"
            }
        }

        fun fromString(value: String): Isactive? {
            return when (value) {
                "비활성" -> INACTIVE
                "활성" -> ACTIVE
                else -> null
            }
        }
    }
}

