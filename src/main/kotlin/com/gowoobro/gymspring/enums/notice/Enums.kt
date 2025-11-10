package com.gowoobro.gymspring.enums.notice


enum class Type {
    0,  // 일반
    1,  // 중요
    2,  // 이벤트
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                0 -> "일반"
                1 -> "중요"
                2 -> "이벤트"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "일반" -> 0
                "중요" -> 1
                "이벤트" -> 2
                else -> null
            }
        }
    }
}


enum class Ispopup {
    0,  // 아니오
    1,  // 예
;

    companion object {
        fun getDisplayName(value: Ispopup): String {
            return when (value) {
                0 -> "아니오"
                1 -> "예"
            }
        }

        fun fromString(value: String): Ispopup? {
            return when (value) {
                "아니오" -> 0
                "예" -> 1
                else -> null
            }
        }
    }
}


enum class Ispush {
    0,  // 아니오
    1,  // 예
;

    companion object {
        fun getDisplayName(value: Ispush): String {
            return when (value) {
                0 -> "아니오"
                1 -> "예"
            }
        }

        fun fromString(value: String): Ispush? {
            return when (value) {
                "아니오" -> 0
                "예" -> 1
                else -> null
            }
        }
    }
}


enum class Target {
    0,  // 전체
    1,  // 회원만
    2,  // 특정회원
;

    companion object {
        fun getDisplayName(value: Target): String {
            return when (value) {
                0 -> "전체"
                1 -> "회원만"
                2 -> "특정회원"
            }
        }

        fun fromString(value: String): Target? {
            return when (value) {
                "전체" -> 0
                "회원만" -> 1
                "특정회원" -> 2
                else -> null
            }
        }
    }
}


enum class Status {
    0,  // 비공개
    1,  // 공개
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                0 -> "비공개"
                1 -> "공개"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "비공개" -> 0
                "공개" -> 1
                else -> null
            }
        }
    }
}

