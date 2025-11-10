package com.gowoobro.gymspring.enums.rocker


enum class Available {
    0,  // 사용중
    1,  // 사용가능
;

    companion object {
        fun getDisplayName(value: Available): String {
            return when (value) {
                0 -> "사용중"
                1 -> "사용가능"
            }
        }

        fun fromString(value: String): Available? {
            return when (value) {
                "사용중" -> 0
                "사용가능" -> 1
                else -> null
            }
        }
    }
}

