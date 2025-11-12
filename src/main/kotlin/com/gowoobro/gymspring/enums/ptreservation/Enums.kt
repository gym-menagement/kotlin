package com.gowoobro.gymspring.enums.ptreservation


enum class Status {
    RESERVED,  // 예약
    COMPLETED,  // 완료
    CANCELLED,  // 취소
    NO_SHOW,  // 노쇼
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                RESERVED -> "예약"
                COMPLETED -> "완료"
                CANCELLED -> "취소"
                NO_SHOW -> "노쇼"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "예약" -> RESERVED
                "완료" -> COMPLETED
                "취소" -> CANCELLED
                "노쇼" -> NO_SHOW
                else -> null
            }
        }
    }
}

