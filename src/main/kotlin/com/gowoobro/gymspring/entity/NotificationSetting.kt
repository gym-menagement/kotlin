package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalTime

import com.gowoobro.gymspring.enums.notificationsetting.NotificationEnabled

@Entity
@Table(name = "notificationsetting_tb")
data class NotificationSetting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ns_id")
    val id: Long = 0,
    @Column(name = "ns_user")
    val userId: Long = 0L,
    @Column(name = "ns_enabled")
    val enabled: NotificationEnabled = NotificationEnabled.ENABLED,  // 전체 알림 ON/OFF
    @Column(name = "ns_membership_expiry")
    val membershipExpiry: NotificationEnabled = NotificationEnabled.ENABLED,  // 이용권 만료 알림
    @Column(name = "ns_membership_near_expiry")
    val membershipNearExpiry: NotificationEnabled = NotificationEnabled.ENABLED,  // 이용권 만료 임박 알림
    @Column(name = "ns_attendance_encourage")
    val attendanceEncourage: NotificationEnabled = NotificationEnabled.ENABLED,  // 출석 독려 알림
    @Column(name = "ns_gym_announcement")
    val gymAnnouncement: NotificationEnabled = NotificationEnabled.ENABLED,  // 체육관 공지사항
    @Column(name = "ns_system_notice")
    val systemNotice: NotificationEnabled = NotificationEnabled.ENABLED,  // 시스템 공지
    @Column(name = "ns_payment_confirm")
    val paymentConfirm: NotificationEnabled = NotificationEnabled.ENABLED,  // 결제 확인
    @Column(name = "ns_pause_expiry")
    val pauseExpiry: NotificationEnabled = NotificationEnabled.ENABLED,  // 일시정지 만료 알림
    @Column(name = "ns_weekly_goal_achieved")
    val weeklyGoalAchieved: NotificationEnabled = NotificationEnabled.ENABLED,  // 주간 목표 달성 알림
    @Column(name = "ns_personal_record")
    val personalRecord: NotificationEnabled = NotificationEnabled.ENABLED,  // 개인 기록 갱신 알림
    @Column(name = "ns_quiet_hours_enabled")
    val quietHoursEnabled: NotificationEnabled = NotificationEnabled.DISABLED,  // 방해 금지 시간 사용
    @Column(name = "ns_quiet_hours_start")
    val quietHoursStart: LocalTime? = null,  // 방해 금지 시작 시간 (예: 22:00)
    @Column(name = "ns_quiet_hours_end")
    val quietHoursEnd: LocalTime? = null,  // 방해 금지 종료 시간 (예: 08:00)
    @Column(name = "ns_createddate")
    val createdDate: LocalDateTime = LocalDateTime.now(),
    @Column(name = "ns_updateddate")
    val updatedDate: LocalDateTime = LocalDateTime.now(),
    @Column(name = "ns_date")
    val date: LocalDateTime = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ns_user", insertable = false, updatable = false)
    var user: User? = null
}

data class NotificationSettingCreateRequest(
    val userId: Long = 0L,
    val enabled: NotificationEnabled = NotificationEnabled.ENABLED,
    val membershipExpiry: NotificationEnabled = NotificationEnabled.ENABLED,
    val membershipNearExpiry: NotificationEnabled = NotificationEnabled.ENABLED,
    val attendanceEncourage: NotificationEnabled = NotificationEnabled.ENABLED,
    val gymAnnouncement: NotificationEnabled = NotificationEnabled.ENABLED,
    val systemNotice: NotificationEnabled = NotificationEnabled.ENABLED,
    val paymentConfirm: NotificationEnabled = NotificationEnabled.ENABLED,
    val pauseExpiry: NotificationEnabled = NotificationEnabled.ENABLED,
    val weeklyGoalAchieved: NotificationEnabled = NotificationEnabled.ENABLED,
    val personalRecord: NotificationEnabled = NotificationEnabled.ENABLED,
    val quietHoursEnabled: NotificationEnabled = NotificationEnabled.DISABLED,
    val quietHoursStart: LocalTime? = null,
    val quietHoursEnd: LocalTime? = null,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val updatedDate: LocalDateTime = LocalDateTime.now(),
    val date: LocalDateTime = LocalDateTime.now(),
)

data class NotificationSettingUpdateRequest(
    val id: Long = 0,
    val userId: Long = 0L,
    val enabled: NotificationEnabled = NotificationEnabled.ENABLED,
    val membershipExpiry: NotificationEnabled = NotificationEnabled.ENABLED,
    val membershipNearExpiry: NotificationEnabled = NotificationEnabled.ENABLED,
    val attendanceEncourage: NotificationEnabled = NotificationEnabled.ENABLED,
    val gymAnnouncement: NotificationEnabled = NotificationEnabled.ENABLED,
    val systemNotice: NotificationEnabled = NotificationEnabled.ENABLED,
    val paymentConfirm: NotificationEnabled = NotificationEnabled.ENABLED,
    val pauseExpiry: NotificationEnabled = NotificationEnabled.ENABLED,
    val weeklyGoalAchieved: NotificationEnabled = NotificationEnabled.ENABLED,
    val personalRecord: NotificationEnabled = NotificationEnabled.ENABLED,
    val quietHoursEnabled: NotificationEnabled = NotificationEnabled.DISABLED,
    val quietHoursStart: LocalTime? = null,
    val quietHoursEnd: LocalTime? = null,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val updatedDate: LocalDateTime = LocalDateTime.now(),
    val date: LocalDateTime = LocalDateTime.now(),
)

data class NotificationSettingPatchRequest(
    val id: Long = 0,
    val userId: Long? = null,
    val enabled: NotificationEnabled? = null,
    val membershipExpiry: NotificationEnabled? = null,
    val membershipNearExpiry: NotificationEnabled? = null,
    val attendanceEncourage: NotificationEnabled? = null,
    val gymAnnouncement: NotificationEnabled? = null,
    val systemNotice: NotificationEnabled? = null,
    val paymentConfirm: NotificationEnabled? = null,
    val pauseExpiry: NotificationEnabled? = null,
    val weeklyGoalAchieved: NotificationEnabled? = null,
    val personalRecord: NotificationEnabled? = null,
    val quietHoursEnabled: NotificationEnabled? = null,
    val quietHoursStart: LocalTime? = null,
    val quietHoursEnd: LocalTime? = null,
    val createdDate: LocalDateTime? = null,
    val updatedDate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class NotificationSettingExtraInfo(
    val enabled: String = "",
    val membershipExpiry: String = "",
    val membershipNearExpiry: String = "",
    val attendanceEncourage: String = "",
    val gymAnnouncement: String = "",
    val systemNotice: String = "",
    val paymentConfirm: String = "",
    val pauseExpiry: String = "",
    val weeklyGoalAchieved: String = "",
    val personalRecord: String = "",
    val quietHoursEnabled: String = "",
    val user: UserResponse? = null,
)

data class NotificationSettingResponse(
    val id: Long,
    val userId: Long,
    val enabled: Int,
    val membershipExpiry: Int,
    val membershipNearExpiry: Int,
    val attendanceEncourage: Int,
    val gymAnnouncement: Int,
    val systemNotice: Int,
    val paymentConfirm: Int,
    val pauseExpiry: Int,
    val weeklyGoalAchieved: Int,
    val personalRecord: Int,
    val quietHoursEnabled: Int,
    val quietHoursStart: String?,
    val quietHoursEnd: String?,
    val createdDate: String,
    val updatedDate: String,
    val date: String,
    val extra: NotificationSettingExtraInfo
) {
    companion object {
        fun from(setting: NotificationSetting): NotificationSettingResponse {
            val userResponse = setting.user?.let { UserResponse.from(it) }

            return NotificationSettingResponse(
                id = setting.id,
                userId = setting.userId,
                enabled = setting.enabled.ordinal,
                membershipExpiry = setting.membershipExpiry.ordinal,
                membershipNearExpiry = setting.membershipNearExpiry.ordinal,
                attendanceEncourage = setting.attendanceEncourage.ordinal,
                gymAnnouncement = setting.gymAnnouncement.ordinal,
                systemNotice = setting.systemNotice.ordinal,
                paymentConfirm = setting.paymentConfirm.ordinal,
                pauseExpiry = setting.pauseExpiry.ordinal,
                weeklyGoalAchieved = setting.weeklyGoalAchieved.ordinal,
                personalRecord = setting.personalRecord.ordinal,
                quietHoursEnabled = setting.quietHoursEnabled.ordinal,
                quietHoursStart = setting.quietHoursStart?.toString(),
                quietHoursEnd = setting.quietHoursEnd?.toString(),
                createdDate = setting.createdDate.toString().replace("T", " "),
                updatedDate = setting.updatedDate.toString().replace("T", " "),
                date = setting.date.toString().replace("T", " "),
                extra = NotificationSettingExtraInfo(
                    enabled = NotificationEnabled.getDisplayName(setting.enabled),
                    membershipExpiry = NotificationEnabled.getDisplayName(setting.membershipExpiry),
                    membershipNearExpiry = NotificationEnabled.getDisplayName(setting.membershipNearExpiry),
                    attendanceEncourage = NotificationEnabled.getDisplayName(setting.attendanceEncourage),
                    gymAnnouncement = NotificationEnabled.getDisplayName(setting.gymAnnouncement),
                    systemNotice = NotificationEnabled.getDisplayName(setting.systemNotice),
                    paymentConfirm = NotificationEnabled.getDisplayName(setting.paymentConfirm),
                    pauseExpiry = NotificationEnabled.getDisplayName(setting.pauseExpiry),
                    weeklyGoalAchieved = NotificationEnabled.getDisplayName(setting.weeklyGoalAchieved),
                    personalRecord = NotificationEnabled.getDisplayName(setting.personalRecord),
                    quietHoursEnabled = NotificationEnabled.getDisplayName(setting.quietHoursEnabled),
                    user = userResponse,
                )
            )
        }
    }
}
