package com.gowoobro.gymspring.enums.notificationhistory


enum class Type {
    NONE,
    GENERAL,  // 일반
    MEMBERSHIP_EXPIRY,  // 이용권만료
    MEMBERSHIP_NEAR_EXPIRY,  // 이용권임박
    ATTENDANCE_ENCOURAGE,  // 출석독려
    GYM_ANNOUNCEMENT,  // 체육관공지
    SYSTEM_NOTICE,  // 시스템공지
    PAYMENT_CONFIRM,  // 결제확인
    PAUSE_EXPIRY,  // 일시정지만료
    WEEKLY_GOAL_ACHIEVED,  // 주간목표달성
    PERSONAL_RECORD,  // 개인기록갱신
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NONE -> ""
                GENERAL -> "일반"
                MEMBERSHIP_EXPIRY -> "이용권만료"
                MEMBERSHIP_NEAR_EXPIRY -> "이용권임박"
                ATTENDANCE_ENCOURAGE -> "출석독려"
                GYM_ANNOUNCEMENT -> "체육관공지"
                SYSTEM_NOTICE -> "시스템공지"
                PAYMENT_CONFIRM -> "결제확인"
                PAUSE_EXPIRY -> "일시정지만료"
                WEEKLY_GOAL_ACHIEVED -> "주간목표달성"
                PERSONAL_RECORD -> "개인기록갱신"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "" -> NONE
                "일반" -> GENERAL
                "이용권만료" -> MEMBERSHIP_EXPIRY
                "이용권임박" -> MEMBERSHIP_NEAR_EXPIRY
                "출석독려" -> ATTENDANCE_ENCOURAGE
                "체육관공지" -> GYM_ANNOUNCEMENT
                "시스템공지" -> SYSTEM_NOTICE
                "결제확인" -> PAYMENT_CONFIRM
                "일시정지만료" -> PAUSE_EXPIRY
                "주간목표달성" -> WEEKLY_GOAL_ACHIEVED
                "개인기록갱신" -> PERSONAL_RECORD
                else -> null
            }
        }
    }
}


enum class Status {
    NONE,
    PENDING,  // 대기중
    SUCCESS,  // 성공
    FAILED,  // 실패
;

    companion object {
        fun getDisplayName(value: Status): String {
            return when (value) {
                NONE -> ""
                PENDING -> "대기중"
                SUCCESS -> "성공"
                FAILED -> "실패"
            }
        }

        fun fromString(value: String): Status? {
            return when (value) {
                "" -> NONE
                "대기중" -> PENDING
                "성공" -> SUCCESS
                "실패" -> FAILED
                else -> null
            }
        }
    }
}

