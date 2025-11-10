package com.gowoobro.gymspring.enums.inquiry


enum class Type {
    0,  // 일반
    1,  // 회원권
    2,  // 환불
    3,  // 시설
    4,  // 기타
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                0 -> "일반"
                1 -> "회원권"
                2 -> "환불"
                3 -> "시설"
                4 -> "기타"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "일반" -> 0
                "회원권" -> 1
                "환불" -> 2
                "시설" -> 3
                "기타" -> 4
                else -> null
            }
        }
    }
}


enum class Status {
    0,  // 대기
    1,  // 답변완료
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                0 -> "대기"
                1 -> "답변완료"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "대기" -> 0
                "답변완료" -> 1
                else -> null
            }
        }
    }
}

