package com.gowoobro.gymspring.enums.user


enum class Level {
    NORMAL,  // 일반
    MANAGER,  // 팀장
    ADMIN,  // 관리자
    SUPERADMIN,  // 승인관리자
    ROOTADMIN,  // 전체관리자
;

    companion object {
        fun getDisplayName(value: Level): String {
            return when (value) {
                NORMAL -> "일반"
                MANAGER -> "팀장"
                ADMIN -> "관리자"
                SUPERADMIN -> "승인관리자"
                ROOTADMIN -> "전체관리자"
            }
        }

        fun fromString(value: String): Level? {
            return when (value) {
                "일반" -> NORMAL
                "팀장" -> MANAGER
                "관리자" -> ADMIN
                "승인관리자" -> SUPERADMIN
                "전체관리자" -> ROOTADMIN
                else -> null
            }
        }
    }
}


enum class Use {
    USE,  // 사용
    NOTUSE,  // 사용안함
;

    companion object {
        fun getDisplayName(value: Use): String {
            return when (value) {
                USE -> "사용"
                NOTUSE -> "사용안함"
            }
        }

        fun fromString(value: String): Use? {
            return when (value) {
                "사용" -> USE
                "사용안함" -> NOTUSE
                else -> null
            }
        }
    }
}


enum class Type {
    NORMAL,  // 일반
    KAKAO,  // 카카오
    NAVER,  // 네이버
    GOOGLE,  // 구글
    APPLE,  // 애플
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NORMAL -> "일반"
                KAKAO -> "카카오"
                NAVER -> "네이버"
                GOOGLE -> "구글"
                APPLE -> "애플"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "일반" -> NORMAL
                "카카오" -> KAKAO
                "네이버" -> NAVER
                "구글" -> GOOGLE
                "애플" -> APPLE
                else -> null
            }
        }
    }
}


enum class Role {
    SUPERVISOR,  // 감독
    COACH,  // 코치
    PARENT,  // 학부모
    PLAYER,  // 현역선수
    USE,  // 동호회
    NORMAL,  // 일반인
;

    companion object {
        fun getDisplayName(value: Role): String {
            return when (value) {
                SUPERVISOR -> "감독"
                COACH -> "코치"
                PARENT -> "학부모"
                PLAYER -> "현역선수"
                USE -> "동호회"
                NORMAL -> "일반인"
            }
        }

        fun fromString(value: String): Role? {
            return when (value) {
                "감독" -> SUPERVISOR
                "코치" -> COACH
                "학부모" -> PARENT
                "현역선수" -> PLAYER
                "동호회" -> USE
                "일반인" -> NORMAL
                else -> null
            }
        }
    }
}

