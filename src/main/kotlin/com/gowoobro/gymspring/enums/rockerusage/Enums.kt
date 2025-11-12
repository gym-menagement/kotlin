package com.gowoobro.gymspring.enums.rockerusage


enum class Status {
    NONE,
    TERMINATED,  // 종료
    IN_USE,  // 사용중
    OVERDUE,  // 연체
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                NONE -> ""
                TERMINATED -> "종료"
                IN_USE -> "사용중"
                OVERDUE -> "연체"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "" -> NONE
                "종료" -> TERMINATED
                "사용중" -> IN_USE
                "연체" -> OVERDUE
                else -> null
            }
        }
    }
}

