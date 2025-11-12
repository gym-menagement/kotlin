package com.gowoobro.gymspring.enums.membershipusage


enum class Type {
    PERIOD_BASED,  // 기간제
    COUNT_BASED,  // 횟수제
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                PERIOD_BASED -> "기간제"
                COUNT_BASED -> "횟수제"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "기간제" -> PERIOD_BASED
                "횟수제" -> COUNT_BASED
                else -> null
            }
        }
    }
}


enum class Status {
    IN_USE,  // 사용중
    PAUSED,  // 일시정지
    EXPIRED,  // 만료
    REFUNDED,  // 환불
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                IN_USE -> "사용중"
                PAUSED -> "일시정지"
                EXPIRED -> "만료"
                REFUNDED -> "환불"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "사용중" -> IN_USE
                "일시정지" -> PAUSED
                "만료" -> EXPIRED
                "환불" -> REFUNDED
                else -> null
            }
        }
    }
}

