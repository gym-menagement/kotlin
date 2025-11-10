package com.gowoobro.gymspring.enums.ptreservation


enum class Status {
    0,  // 예약
    1,  // 완료
    2,  // 취소
    3,  // 노쇼
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                0 -> "예약"
                1 -> "완료"
                2 -> "취소"
                3 -> "노쇼"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "예약" -> 0
                "완료" -> 1
                "취소" -> 2
                "노쇼" -> 3
                else -> null
            }
        }
    }
}

