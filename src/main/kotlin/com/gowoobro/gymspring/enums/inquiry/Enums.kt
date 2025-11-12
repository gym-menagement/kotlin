package com.gowoobro.gymspring.enums.inquiry


enum class Type {
    GENERAL,  // 일반
    MEMBERSHIP,  // 회원권
    REFUND,  // 환불
    FACILITY,  // 시설
    OTHER,  // 기타
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                GENERAL -> "일반"
                MEMBERSHIP -> "회원권"
                REFUND -> "환불"
                FACILITY -> "시설"
                OTHER -> "기타"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "일반" -> GENERAL
                "회원권" -> MEMBERSHIP
                "환불" -> REFUND
                "시설" -> FACILITY
                "기타" -> OTHER
                else -> null
            }
        }
    }
}


enum class Status {
    WAITING,  // 대기
    ANSWERED,  // 답변완료
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                WAITING -> "대기"
                ANSWERED -> "답변완료"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "대기" -> WAITING
                "답변완료" -> ANSWERED
                else -> null
            }
        }
    }
}

