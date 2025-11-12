package com.gowoobro.gymspring.enums.rocker


enum class Available {
    NONE,
    IN_USE,  // 사용중
    AVAILABLE,  // 사용가능
;

    companion object {
        fun getDisplayName(value: Available): String {
            return when (value) {
                NONE -> ""
                IN_USE -> "사용중"
                AVAILABLE -> "사용가능"
            }
        }

        fun fromString(value: String): Available? {
            return when (value) {
                "" -> NONE
                "사용중" -> IN_USE
                "사용가능" -> AVAILABLE
                else -> null
            }
        }
    }
}

