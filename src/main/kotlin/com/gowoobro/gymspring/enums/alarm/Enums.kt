package com.gowoobro.gymspring.enums.alarm


enum class Type {
    NOTICE,  // 공지
    WARNING,  // 경고
    ERROR,  // 에러
    INFO,  // 정보
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NOTICE -> "공지"
                WARNING -> "경고"
                ERROR -> "에러"
                INFO -> "정보"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "공지" -> NOTICE
                "경고" -> WARNING
                "에러" -> ERROR
                "정보" -> INFO
                else -> null
            }
        }
    }
}


enum class Status {
    SUCCESS,  // 성공
    FAIL,  // 실패
    PENDING,  // 대기
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                SUCCESS -> "성공"
                FAIL -> "실패"
                PENDING -> "대기"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "성공" -> SUCCESS
                "실패" -> FAIL
                "대기" -> PENDING
                else -> null
            }
        }
    }
}

