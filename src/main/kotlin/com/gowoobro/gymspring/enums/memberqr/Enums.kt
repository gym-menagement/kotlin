package com.gowoobro.gymspring.enums.memberqr


enum class Isactive {
    0,  // 비활성
    1,  // 활성
;

    companion object {
        fun getDisplayName(value: Isactive): String {
            return when (value) {
                0 -> "비활성"
                1 -> "활성"
            }
        }

        fun fromString(value: String): Isactive? {
            return when (value) {
                "비활성" -> 0
                "활성" -> 1
                else -> null
            }
        }
    }
}

