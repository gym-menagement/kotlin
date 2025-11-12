package com.gowoobro.gymspring.enums.trainermember


enum class Status {
    NONE,
    TERMINATED,  // 종료
    IN_PROGRESS,  // 진행중
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                NONE -> ""
                TERMINATED -> "종료"
                IN_PROGRESS -> "진행중"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "" -> NONE
                "종료" -> TERMINATED
                "진행중" -> IN_PROGRESS
                else -> null
            }
        }
    }
}

