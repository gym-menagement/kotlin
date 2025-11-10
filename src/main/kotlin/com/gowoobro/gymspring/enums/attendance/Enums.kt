package com.gowoobro.gymspring.enums.attendance


enum class Type {
    0,  // 입장
    1,  // 퇴장
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                0 -> "입장"
                1 -> "퇴장"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "입장" -> 0
                "퇴장" -> 1
                else -> null
            }
        }
    }
}


enum class Method {
    0,  // QR코드
    1,  // 수동
    2,  // 카드
;

    companion object {
        fun getDisplayName(value: Method): String {
            return when (value) {
                0 -> "QR코드"
                1 -> "수동"
                2 -> "카드"
            }
        }

        fun fromString(value: String): Method? {
            return when (value) {
                "QR코드" -> 0
                "수동" -> 1
                "카드" -> 2
                else -> null
            }
        }
    }
}


enum class Status {
    0,  // 정상
    1,  // 지각
    2,  // 무단입장
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                0 -> "정상"
                1 -> "지각"
                2 -> "무단입장"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "정상" -> 0
                "지각" -> 1
                "무단입장" -> 2
                else -> null
            }
        }
    }
}

