package com.gowoobro.gymspring.enums.trainermember


enum class Status {
    0,  // 종료
    1,  // 진행중
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                0 -> "종료"
                1 -> "진행중"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "종료" -> 0
                "진행중" -> 1
                else -> null
            }
        }
    }
}

