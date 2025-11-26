package com.gowoobro.gymspring.enums.usehealth


enum class Status {
    NONE,
    TERMINATED,  // 종료
    USE,  // 사용중
    PAUSED,  // 일시정지
    EXPIRED,  // 만료
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                NONE -> ""
                TERMINATED -> "종료"
                USE -> "사용중"
                PAUSED -> "일시정지"
                EXPIRED -> "만료"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "" -> NONE
                "종료" -> TERMINATED
                "사용중" -> USE
                "일시정지" -> PAUSED
                "만료" -> EXPIRED
                else -> null
            }
        }
    }
}

