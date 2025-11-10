package com.gowoobro.gymspring.enums.systemlog


enum class Type {
    SYSTEM,  // 시스템
    USER,  // 사용자
    ADMIN,  // 관리자
    ERROR,  // 에러
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                SYSTEM -> "시스템"
                USER -> "사용자"
                ADMIN -> "관리자"
                ERROR -> "에러"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "시스템" -> SYSTEM
                "사용자" -> USER
                "관리자" -> ADMIN
                "에러" -> ERROR
                else -> null
            }
        }
    }
}


enum class Result {
    SUCCESS,  // 성공
    FAIL,  // 실패
;

    companion object {
        fun getDisplayName(value: Result): String {
            return when (value) {
                SUCCESS -> "성공"
                FAIL -> "실패"
            }
        }

        fun fromString(value: String): Result? {
            return when (value) {
                "성공" -> SUCCESS
                "실패" -> FAIL
                else -> null
            }
        }
    }
}

