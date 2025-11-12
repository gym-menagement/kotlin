package com.gowoobro.gymspring.enums.rocker


enum class Available {
    IN_USE,  // 사용중
    AVAILABLE,  // 사용가능
;

    companion object {
        fun getDisplayName(value: Available): String {
            return when (value) {
                IN_USE -> "사용중"
                AVAILABLE -> "사용가능"
            }
        }

        fun fromString(value: String): Available? {
            return when (value) {
                "사용중" -> IN_USE
                "사용가능" -> AVAILABLE
                else -> null
            }
        }
    }
}

