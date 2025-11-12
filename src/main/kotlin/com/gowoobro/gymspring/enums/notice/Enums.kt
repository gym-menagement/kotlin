package com.gowoobro.gymspring.enums.notice


enum class Type {
    GENERAL,  // 일반
    IMPORTANT,  // 중요
    EVENT,  // 이벤트
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                GENERAL -> "일반"
                IMPORTANT -> "중요"
                EVENT -> "이벤트"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "일반" -> GENERAL
                "중요" -> IMPORTANT
                "이벤트" -> EVENT
                else -> null
            }
        }
    }
}


enum class Ispopup {
    NO,  // 아니오
    YES,  // 예
;

    companion object {
        fun getDisplayName(value: Ispopup): String {
            return when (value) {
                NO -> "아니오"
                YES -> "예"
            }
        }

        fun fromString(value: String): Ispopup? {
            return when (value) {
                "아니오" -> NO
                "예" -> YES
                else -> null
            }
        }
    }
}


enum class Ispush {
    NO,  // 아니오
    YES,  // 예
;

    companion object {
        fun getDisplayName(value: Ispush): String {
            return when (value) {
                NO -> "아니오"
                YES -> "예"
            }
        }

        fun fromString(value: String): Ispush? {
            return when (value) {
                "아니오" -> NO
                "예" -> YES
                else -> null
            }
        }
    }
}


enum class Target {
    ALL,  // 전체
    MEMBERS_ONLY,  // 회원만
    SPECIFIC_MEMBERS,  // 특정회원
;

    companion object {
        fun getDisplayName(value: Target): String {
            return when (value) {
                ALL -> "전체"
                MEMBERS_ONLY -> "회원만"
                SPECIFIC_MEMBERS -> "특정회원"
            }
        }

        fun fromString(value: String): Target? {
            return when (value) {
                "전체" -> ALL
                "회원만" -> MEMBERS_ONLY
                "특정회원" -> SPECIFIC_MEMBERS
                else -> null
            }
        }
    }
}


enum class Status {
    PRIVATE,  // 비공개
    PUBLIC,  // 공개
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                PRIVATE -> "비공개"
                PUBLIC -> "공개"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "비공개" -> PRIVATE
                "공개" -> PUBLIC
                else -> null
            }
        }
    }
}

