package com.gowoobro.gymspring.enums.notificationsetting

enum class NotificationEnabled {
    ENABLED,    // 알림 켜짐
    DISABLED;   // 알림 꺼짐

    companion object {
        fun getDisplayName(enabled: NotificationEnabled): String {
            return when (enabled) {
                ENABLED -> "켜짐"
                DISABLED -> "꺼짐"
            }
        }
    }
}
