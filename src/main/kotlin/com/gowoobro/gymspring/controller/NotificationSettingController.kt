package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.NotificationSetting
import com.gowoobro.gymspring.entity.NotificationSettingResponse
import com.gowoobro.gymspring.repository.NotificationSettingRepository
import com.gowoobro.gymspring.enums.notificationsetting.NotificationEnabled
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.LocalTime

@RestController
@RequestMapping("/api/notification-settings")
class NotificationSettingController(
    private val notificationSettingRepository: NotificationSettingRepository
) {

    /**
     * 사용자 알림 설정 조회
     * GET /api/notification-settings/user/{userId}
     */
    @GetMapping("/user/{userId}")
    fun getUserNotificationSetting(
        @PathVariable userId: Long
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val setting = notificationSettingRepository.findByUserId(userId)

            if (setting != null) {
                ResponseEntity.ok(mapOf<String, Any>(
                    "success" to true,
                    "data" to NotificationSettingResponse.from(setting)
                ))
            } else {
                // 설정이 없으면 기본 설정 반환
                ResponseEntity.ok(mapOf<String, Any>(
                    "success" to true,
                    "message" to "No settings found. Using default settings."
                ))
            }
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to get notification settings: ${e.message}"
            ))
        }
    }

    /**
     * 사용자 알림 설정 생성 (첫 설정)
     * POST /api/notification-settings/user/{userId}
     */
    @PostMapping("/user/{userId}")
    fun createUserNotificationSetting(
        @PathVariable userId: Long
    ): ResponseEntity<Map<String, Any>> {
        return try {
            // 이미 설정이 있는지 확인
            val existing = notificationSettingRepository.findByUserId(userId)
            if (existing != null) {
                return ResponseEntity.ok(mapOf(
                    "success" to false,
                    "message" to "Notification settings already exist for this user",
                    "data" to NotificationSettingResponse.from(existing)
                ))
            }

            // 기본 설정으로 생성 (모두 켜짐)
            val newSetting = NotificationSetting(
                userId = userId,
                enabled = NotificationEnabled.ENABLED,
                membershipExpiry = NotificationEnabled.ENABLED,
                membershipNearExpiry = NotificationEnabled.ENABLED,
                attendanceEncourage = NotificationEnabled.ENABLED,
                gymAnnouncement = NotificationEnabled.ENABLED,
                systemNotice = NotificationEnabled.ENABLED,
                paymentConfirm = NotificationEnabled.ENABLED,
                pauseExpiry = NotificationEnabled.ENABLED,
                weeklyGoalAchieved = NotificationEnabled.ENABLED,
                personalRecord = NotificationEnabled.ENABLED,
                quietHoursEnabled = NotificationEnabled.DISABLED,
                quietHoursStart = null,
                quietHoursEnd = null,
                createdDate = LocalDateTime.now(),
                updatedDate = LocalDateTime.now(),
                date = LocalDateTime.now()
            )

            val saved = notificationSettingRepository.save(newSetting)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Notification settings created successfully",
                "data" to NotificationSettingResponse.from(saved)
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to create notification settings: ${e.message}"
            ))
        }
    }

    /**
     * 전체 알림 ON/OFF 토글
     * PATCH /api/notification-settings/user/{userId}/toggle-all
     */
    @PatchMapping("/user/{userId}/toggle-all")
    fun toggleAllNotifications(
        @PathVariable userId: Long,
        @RequestParam enabled: Boolean
    ): ResponseEntity<Map<String, Any>> {
        return try {
            var setting = notificationSettingRepository.findByUserId(userId)
                ?: notificationSettingRepository.save(NotificationSetting(userId = userId))

            val enabledState = if (enabled) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED

            setting = notificationSettingRepository.save(setting.copy(
                enabled = enabledState,
                updatedDate = LocalDateTime.now()
            ))

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "All notifications ${if (enabled) "enabled" else "disabled"}",
                "data" to NotificationSettingResponse.from(setting)
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to toggle notifications: ${e.message}"
            ))
        }
    }

    /**
     * 특정 알림 타입 ON/OFF 설정
     * PATCH /api/notification-settings/user/{userId}/type
     */
    @PatchMapping("/user/{userId}/type")
    fun updateNotificationType(
        @PathVariable userId: Long,
        @RequestParam(required = false) membershipExpiry: Boolean?,
        @RequestParam(required = false) membershipNearExpiry: Boolean?,
        @RequestParam(required = false) attendanceEncourage: Boolean?,
        @RequestParam(required = false) gymAnnouncement: Boolean?,
        @RequestParam(required = false) systemNotice: Boolean?,
        @RequestParam(required = false) paymentConfirm: Boolean?,
        @RequestParam(required = false) pauseExpiry: Boolean?,
        @RequestParam(required = false) weeklyGoalAchieved: Boolean?,
        @RequestParam(required = false) personalRecord: Boolean?
    ): ResponseEntity<Map<String, Any>> {
        return try {
            var setting = notificationSettingRepository.findByUserId(userId)
                ?: notificationSettingRepository.save(NotificationSetting(userId = userId))

            setting = notificationSettingRepository.save(setting.copy(
                membershipExpiry = membershipExpiry?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.membershipExpiry,
                membershipNearExpiry = membershipNearExpiry?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.membershipNearExpiry,
                attendanceEncourage = attendanceEncourage?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.attendanceEncourage,
                gymAnnouncement = gymAnnouncement?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.gymAnnouncement,
                systemNotice = systemNotice?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.systemNotice,
                paymentConfirm = paymentConfirm?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.paymentConfirm,
                pauseExpiry = pauseExpiry?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.pauseExpiry,
                weeklyGoalAchieved = weeklyGoalAchieved?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.weeklyGoalAchieved,
                personalRecord = personalRecord?.let { if (it) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED } ?: setting.personalRecord,
                updatedDate = LocalDateTime.now()
            ))

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Notification type settings updated",
                "data" to NotificationSettingResponse.from(setting)
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to update notification type settings: ${e.message}"
            ))
        }
    }

    /**
     * 방해 금지 시간 설정
     * PATCH /api/notification-settings/user/{userId}/quiet-hours
     * @param enabled 방해 금지 사용 여부
     * @param startTime 시작 시간 (HH:mm 형식, 예: "22:00")
     * @param endTime 종료 시간 (HH:mm 형식, 예: "08:00")
     */
    @PatchMapping("/user/{userId}/quiet-hours")
    fun updateQuietHours(
        @PathVariable userId: Long,
        @RequestParam enabled: Boolean,
        @RequestParam(required = false) startTime: String?,
        @RequestParam(required = false) endTime: String?
    ): ResponseEntity<Map<String, Any>> {
        return try {
            var setting = notificationSettingRepository.findByUserId(userId)
                ?: notificationSettingRepository.save(NotificationSetting(userId = userId))

            val quietStart = if (enabled && startTime != null) {
                LocalTime.parse(startTime)
            } else {
                null
            }

            val quietEnd = if (enabled && endTime != null) {
                LocalTime.parse(endTime)
            } else {
                null
            }

            setting = notificationSettingRepository.save(setting.copy(
                quietHoursEnabled = if (enabled) NotificationEnabled.ENABLED else NotificationEnabled.DISABLED,
                quietHoursStart = quietStart,
                quietHoursEnd = quietEnd,
                updatedDate = LocalDateTime.now()
            ))

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Quiet hours ${if (enabled) "enabled" else "disabled"}",
                "data" to NotificationSettingResponse.from(setting)
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to update quiet hours: ${e.message}"
            ))
        }
    }

    /**
     * 알림 설정 삭제 (기본값으로 초기화)
     * DELETE /api/notification-settings/user/{userId}
     */
    @DeleteMapping("/user/{userId}")
    fun deleteUserNotificationSetting(
        @PathVariable userId: Long
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val setting = notificationSettingRepository.findByUserId(userId)

            if (setting != null) {
                notificationSettingRepository.delete(setting)
                ResponseEntity.ok(mapOf(
                    "success" to true,
                    "message" to "Notification settings deleted. Will use default settings."
                ))
            } else {
                ResponseEntity.ok(mapOf(
                    "success" to false,
                    "message" to "No notification settings found for this user"
                ))
            }
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to delete notification settings: ${e.message}"
            ))
        }
    }

    /**
     * 알림을 보낼지 확인하는 헬퍼 함수 (다른 서비스에서 사용)
     * GET /api/notification-settings/user/{userId}/should-send?type={type}
     * @param type 알림 타입 (0=GENERAL, 1=MEMBERSHIP_EXPIRY, 2=MEMBERSHIP_NEAR_EXPIRY, 3=ATTENDANCE_ENCOURAGE,
     *                            4=GYM_ANNOUNCEMENT, 5=SYSTEM_NOTICE, 6=PAYMENT_CONFIRM, 7=PAUSE_EXPIRY,
     *                            8=WEEKLY_GOAL_ACHIEVED, 9=PERSONAL_RECORD)
     */
    @GetMapping("/user/{userId}/should-send")
    fun shouldSendNotification(
        @PathVariable userId: Long,
        @RequestParam type: Int
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val setting = notificationSettingRepository.findByUserId(userId)

            // 설정이 없으면 모두 허용 (기본값)
            if (setting == null) {
                return ResponseEntity.ok(mapOf(
                    "success" to true,
                    "shouldSend" to true,
                    "reason" to "No settings found. Using default (enabled)."
                ))
            }

            // 전체 알림이 꺼져 있으면 거부
            if (setting.enabled == NotificationEnabled.DISABLED) {
                return ResponseEntity.ok(mapOf(
                    "success" to true,
                    "shouldSend" to false,
                    "reason" to "All notifications are disabled."
                ))
            }

            // 방해 금지 시간 확인
            if (setting.quietHoursEnabled == NotificationEnabled.ENABLED) {
                val now = LocalTime.now()
                val start = setting.quietHoursStart
                val end = setting.quietHoursEnd

                if (start != null && end != null) {
                    val isQuietTime = if (start < end) {
                        now in start..end
                    } else {
                        // 시작 시간이 종료 시간보다 늦은 경우 (예: 22:00 ~ 08:00)
                        now >= start || now <= end
                    }

                    if (isQuietTime) {
                        return ResponseEntity.ok(mapOf(
                            "success" to true,
                            "shouldSend" to false,
                            "reason" to "Currently in quiet hours ($start ~ $end)."
                        ))
                    }
                }
            }

            // 타입별 설정 확인
            val typeEnabled = when (type) {
                0 -> true // GENERAL은 항상 허용
                1 -> setting.membershipExpiry == NotificationEnabled.ENABLED
                2 -> setting.membershipNearExpiry == NotificationEnabled.ENABLED
                3 -> setting.attendanceEncourage == NotificationEnabled.ENABLED
                4 -> setting.gymAnnouncement == NotificationEnabled.ENABLED
                5 -> setting.systemNotice == NotificationEnabled.ENABLED
                6 -> setting.paymentConfirm == NotificationEnabled.ENABLED
                7 -> setting.pauseExpiry == NotificationEnabled.ENABLED
                8 -> setting.weeklyGoalAchieved == NotificationEnabled.ENABLED
                9 -> setting.personalRecord == NotificationEnabled.ENABLED
                else -> true
            }

            ResponseEntity.ok(mapOf(
                "success" to true,
                "shouldSend" to typeEnabled,
                "reason" to if (typeEnabled) "Type is enabled." else "Type is disabled."
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to check notification settings: ${e.message}",
                "shouldSend" to true // 에러 시 기본값으로 허용
            ))
        }
    }
}
