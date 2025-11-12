package com.gowoobro.gymspring.enums.appversion


enum class Forceupdate {
    NO,  // 아니오
    YES,  // 예
;

    companion object {
        fun getDisplayName(value: Forceupdate): String {
            return when (value) {
                NO -> "아니오"
                YES -> "예"
            }
        }

        fun fromString(value: String): Forceupdate? {
            return when (value) {
                "아니오" -> NO
                "예" -> YES
                else -> null
            }
        }
    }
}


enum class Status {
    INACTIVE,  // 비활성
    ACTIVE,  // 활성
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                INACTIVE -> "비활성"
                ACTIVE -> "활성"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "비활성" -> INACTIVE
                "활성" -> ACTIVE
                else -> null
            }
        }
    }
}

