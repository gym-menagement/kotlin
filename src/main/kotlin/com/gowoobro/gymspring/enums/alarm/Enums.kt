package com.gowoobro.gymspring.enums.alarm


enum class Type {
    NOTICE,  // 공지
    ALARM,  // 알람
    COMMENT,  // 게시판
    MATCH,  // 경기결과
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NOTICE -> "공지"
                ALARM -> "알람"
                COMMENT -> "게시판"
                MATCH -> "경기결과"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "공지" -> NOTICE
                "알람" -> ALARM
                "게시판" -> COMMENT
                "경기결과" -> MATCH
                else -> null
            }
        }
    }
}


enum class Status {
    SUCCESS,  // 성공
    FAIL,  // 실패
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                SUCCESS -> "성공"
                FAIL -> "실패"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "성공" -> SUCCESS
                "실패" -> FAIL
                else -> null
            }
        }
    }
}

