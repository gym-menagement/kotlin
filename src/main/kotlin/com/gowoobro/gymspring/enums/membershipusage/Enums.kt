package com.gowoobro.gymspring.enums.membershipusage


enum class Type {
    0,  // 기간제
    1,  // 횟수제
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                0 -> "기간제"
                1 -> "횟수제"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "기간제" -> 0
                "횟수제" -> 1
                else -> null
            }
        }
    }
}


enum class Status {
    0,  // 사용중
    1,  // 일시정지
    2,  // 만료
    3,  // 환불
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                0 -> "사용중"
                1 -> "일시정지"
                2 -> "만료"
                3 -> "환불"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "사용중" -> 0
                "일시정지" -> 1
                "만료" -> 2
                "환불" -> 3
                else -> null
            }
        }
    }
}

