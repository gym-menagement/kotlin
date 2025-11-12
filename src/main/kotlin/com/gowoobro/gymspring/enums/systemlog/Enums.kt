package com.gowoobro.gymspring.enums.systemlog


enum class Type {
    NONE,
    LOGIN,  // 로그인
    CRAWLING,  // 크롤링
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NONE -> ""
                LOGIN -> "로그인"
                CRAWLING -> "크롤링"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "" -> NONE
                "로그인" -> LOGIN
                "크롤링" -> CRAWLING
                else -> null
            }
        }
    }
}


enum class Result {
    NONE,
    SUCCESS,  // 성공
    FAIL,  // 실패
;

    companion object {
        fun getDisplayName(value: Result): String {
            return when (value) {
                NONE -> ""
                SUCCESS -> "성공"
                FAIL -> "실패"
            }
        }

        fun fromString(value: String): Result? {
            return when (value) {
                "" -> NONE
                "성공" -> SUCCESS
                "실패" -> FAIL
                else -> null
            }
        }
    }
}

