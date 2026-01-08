package com.gowoobro.gymspring.enums.notificationhistory

enum class SendStatus {
    PENDING,    // 전송 대기
    SUCCESS,    // 전송 성공
    FAILED;     // 전송 실패

    companion object {
        fun getDisplayName(status: SendStatus): String {
            return when (status) {
                PENDING -> "대기"
                SUCCESS -> "성공"
                FAILED -> "실패"
            }
        }
    }
}
