package com.gowoobro.gymspring.enums.attendance


enum class Type {
    ENTRY,  // 입장
    EXIT,  // 퇴장
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                ENTRY -> "입장"
                EXIT -> "퇴장"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "입장" -> ENTRY
                "퇴장" -> EXIT
                else -> null
            }
        }
    }
}


enum class Method {
    QR_CODE,  // QR코드
    MANUAL,  // 수동
    CARD,  // 카드
;

    companion object {
        fun getDisplayName(value: Method): String {
            return when (value) {
                QR_CODE -> "QR코드"
                MANUAL -> "수동"
                CARD -> "카드"
            }
        }

        fun fromString(value: String): Method? {
            return when (value) {
                "QR코드" -> QR_CODE
                "수동" -> MANUAL
                "카드" -> CARD
                else -> null
            }
        }
    }
}


enum class Status {
    NORMAL,  // 정상
    LATE,  // 지각
    UNAUTHORIZED,  // 무단입장
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                NORMAL -> "정상"
                LATE -> "지각"
                UNAUTHORIZED -> "무단입장"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "정상" -> NORMAL
                "지각" -> LATE
                "무단입장" -> UNAUTHORIZED
                else -> null
            }
        }
    }
}

