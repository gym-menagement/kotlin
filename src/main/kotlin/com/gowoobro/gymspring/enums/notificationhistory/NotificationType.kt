package com.gowoobro.gymspring.enums.notificationhistory

enum class NotificationType {
    GENERAL,                // 일반 알림
    MEMBERSHIP_EXPIRY,      // 이용권 만료 알림
    MEMBERSHIP_NEAR_EXPIRY, // 이용권 만료 임박 알림
    ATTENDANCE_ENCOURAGE,   // 출석 독려 알림 (3일 미출석)
    GYM_ANNOUNCEMENT,       // 체육관 공지사항
    SYSTEM_NOTICE,          // 시스템 공지
    PAYMENT_CONFIRM,        // 결제 확인
    PAUSE_EXPIRY,           // 일시정지 만료 알림
    WEEKLY_GOAL_ACHIEVED,   // 주간 운동 목표 달성 알림
    PERSONAL_RECORD,        // 개인 기록 갱신 알림
    TEST;                   // 테스트 알림

    companion object {
        fun getDisplayName(type: NotificationType): String {
            return when (type) {
                GENERAL -> "일반 알림"
                MEMBERSHIP_EXPIRY -> "이용권 만료"
                MEMBERSHIP_NEAR_EXPIRY -> "이용권 만료 임박"
                ATTENDANCE_ENCOURAGE -> "출석 독려"
                GYM_ANNOUNCEMENT -> "체육관 공지"
                SYSTEM_NOTICE -> "시스템 공지"
                PAYMENT_CONFIRM -> "결제 확인"
                PAUSE_EXPIRY -> "일시정지 만료"
                WEEKLY_GOAL_ACHIEVED -> "주간 목표 달성"
                PERSONAL_RECORD -> "개인 기록 갱신"
                TEST -> "테스트"
            }
        }
    }
}
