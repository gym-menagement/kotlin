package com.gowoobro.gymspring.enums.rockerusage


enum class Status {
    0,  // 종료
    1,  // 사용중
    2,  // 연체
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                0 -> "종료"
                1 -> "사용중"
                2 -> "연체"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "종료" -> 0
                "사용중" -> 1
                "연체" -> 2
                else -> null
            }
        }
    }
}

