package com.gowoobro.gymspring.enums.appversion


enum class Forceupdate {
    0,  // 아니오
    1,  // 예
;

    companion object {
        fun getDisplayName(value: Forceupdate): String {
            return when (value) {
                0 -> "아니오"
                1 -> "예"
            }
        }

        fun fromString(value: String): Forceupdate? {
            return when (value) {
                "아니오" -> 0
                "예" -> 1
                else -> null
            }
        }
    }
}


enum class Status {
    0,  // 비활성
    1,  // 활성
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                0 -> "비활성"
                1 -> "활성"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "비활성" -> 0
                "활성" -> 1
                else -> null
            }
        }
    }
}

