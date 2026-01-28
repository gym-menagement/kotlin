package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Notificationhistory
import com.gowoobro.gymspring.entity.Notificationsetting
import com.gowoobro.gymspring.enums.notificationhistory.Type
import com.gowoobro.gymspring.repository.NotificationhistoryRepository
import com.gowoobro.gymspring.repository.NotificationsettingRepository
import com.gowoobro.gymspring.service.FcmService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * 커스텀 알림 API Controller
 * 자동 생성된 Controller와 분리하여 비즈니스 로직 관리
 */
@RestController
@RequestMapping("/api/notification")
class NotificationApiController(
    private val notificationhistoryRepository: NotificationhistoryRepository,
    private val notificationsettingRepository: NotificationsettingRepository,
    private val fcmService: FcmService
) {

    // ==================== 알림 이력 조회 API ====================

    /**
     * 사용자가 받은 알림 이력 조회
     */
    @GetMapping("/history/user/{userId}")
    fun getUserHistory(
        @PathVariable userId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        val pageable = PageRequest.of(page, size)
        val historyPage: Page<Notificationhistory> = notificationhistoryRepository.findByReceiverOrderBySentdateDesc(userId, pageable)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "content" to historyPage.content.map { toHistoryResponse(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages
            )
        )
    }

    /**
     * 체육관 관련 알림 이력 조회
     */
    @GetMapping("/history/gym/{gymId}")
    fun getGymHistory(
        @PathVariable gymId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        val pageable = PageRequest.of(page, size)
        val historyPage: Page<Notificationhistory> = notificationhistoryRepository.findByGymOrderBySentdateDesc(gymId, pageable)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "content" to historyPage.content.map { toHistoryResponse(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages
            )
        )
    }

    /**
     * 특정 알림 타입의 이력 조회
     */
    @GetMapping("/history/type/{type}")
    fun getHistoryByType(
        @PathVariable type: Int,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        val pageable = PageRequest.of(page, size)
        val typeEnum = Type.entries.getOrElse(type) { Type.GENERAL }
        val historyPage: Page<Notificationhistory> = notificationhistoryRepository.findByTypeOrderBySentdateDesc(typeEnum, pageable)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "content" to historyPage.content.map { toHistoryResponse(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages
            )
        )
    }

    /**
     * 사용자의 특정 타입 알림 이력 조회
     */
    @GetMapping("/history/user/{userId}/type/{type}")
    fun getUserHistoryByType(
        @PathVariable userId: Long,
        @PathVariable type: Int,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        val pageable = PageRequest.of(page, size)
        val typeEnum = Type.entries.getOrElse(type) { Type.GENERAL }
        val historyPage: Page<Notificationhistory> = notificationhistoryRepository.findByReceiverAndTypeOrderBySentdateDesc(userId, typeEnum, pageable)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "content" to historyPage.content.map { toHistoryResponse(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages
            )
        )
    }

    /**
     * 특정 알림 상세 조회
     */
    @GetMapping("/history/{id}")
    fun getHistoryById(@PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        val history = notificationhistoryRepository.findById(id).orElse(null)
            ?: return ResponseEntity.ok(mapOf("success" to false, "message" to "알림을 찾을 수 없습니다"))

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "data" to toHistoryResponse(history)
            )
        )
    }

    // ==================== 알림 설정 API ====================

    /**
     * 사용자 알림 설정 조회
     */
    @GetMapping("/settings/user/{userId}")
    fun getUserSettings(@PathVariable userId: Long): ResponseEntity<Map<String, Any?>> {
        val setting = notificationsettingRepository.findByUser(userId).orElse(null)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "data" to if (setting != null) toSettingResponse(setting) else null
            )
        )
    }

    /**
     * 사용자 알림 설정 생성
     */
    @PostMapping("/settings/user/{userId}")
    fun createUserSetting(@PathVariable userId: Long): ResponseEntity<Map<String, Any>> {
        // 이미 존재하는지 확인
        if (notificationsettingRepository.findByUser(userId).isPresent) {
            return ResponseEntity.ok(
                mapOf(
                    "success" to false,
                    "message" to "이미 알림 설정이 존재합니다"
                )
            )
        }

        // 기본 설정으로 생성
        val setting = Notificationsetting(
            userId = userId,
            createddate = LocalDateTime.now(),
            updateddate = LocalDateTime.now(),
            date = LocalDateTime.now()
        )
        notificationsettingRepository.save(setting)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "message" to "알림 설정이 생성되었습니다",
                "data" to toSettingResponse(setting)
            )
        )
    }

    /**
     * 전체 알림 ON/OFF 토글
     */
    @PatchMapping("/settings/user/{userId}/toggle-all")
    fun toggleAllNotifications(
        @PathVariable userId: Long,
        @RequestParam enabled: Boolean
    ): ResponseEntity<Map<String, Any>> {
        val setting = notificationsettingRepository.findByUser(userId).orElse(null)
            ?: return ResponseEntity.ok(mapOf("success" to false, "message" to "알림 설정을 찾을 수 없습니다"))

        val updated = setting.copy(
            enabled = if (enabled) com.gowoobro.gymspring.enums.notificationsetting.Enabled.ENABLED
                     else com.gowoobro.gymspring.enums.notificationsetting.Enabled.DISABLED,
            updateddate = LocalDateTime.now()
        )
        notificationsettingRepository.save(updated)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "message" to "전체 알림이 ${if (enabled) "켜졌습니다" else "꺼졌습니다"}",
                "data" to toSettingResponse(updated)
            )
        )
    }

    /**
     * 특정 알림 타입 설정
     */
    @PatchMapping("/settings/user/{userId}/type")
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
        val setting = notificationsettingRepository.findByUser(userId).orElse(null)
            ?: return ResponseEntity.ok(mapOf("success" to false, "message" to "알림 설정을 찾을 수 없습니다"))

        val toEnabled = { b: Boolean ->
            if (b) com.gowoobro.gymspring.enums.notificationsetting.Enabled.ENABLED
            else com.gowoobro.gymspring.enums.notificationsetting.Enabled.DISABLED
        }

        val updated = setting.copy(
            membershipexpiry = membershipExpiry?.let { com.gowoobro.gymspring.enums.notificationsetting.Membershipexpiry.valueOf(toEnabled(it).name) } ?: setting.membershipexpiry,
            membershipnear = membershipNearExpiry?.let { com.gowoobro.gymspring.enums.notificationsetting.Membershipnear.valueOf(toEnabled(it).name) } ?: setting.membershipnear,
            attendanceenc = attendanceEncourage?.let { com.gowoobro.gymspring.enums.notificationsetting.Attendanceenc.valueOf(toEnabled(it).name) } ?: setting.attendanceenc,
            gymannounce = gymAnnouncement?.let { com.gowoobro.gymspring.enums.notificationsetting.Gymannounce.valueOf(toEnabled(it).name) } ?: setting.gymannounce,
            systemnotice = systemNotice?.let { com.gowoobro.gymspring.enums.notificationsetting.Systemnotice.valueOf(toEnabled(it).name) } ?: setting.systemnotice,
            paymentconfirm = paymentConfirm?.let { com.gowoobro.gymspring.enums.notificationsetting.Paymentconfirm.valueOf(toEnabled(it).name) } ?: setting.paymentconfirm,
            pauseexpiry = pauseExpiry?.let { com.gowoobro.gymspring.enums.notificationsetting.Pauseexpiry.valueOf(toEnabled(it).name) } ?: setting.pauseexpiry,
            weeklygoal = weeklyGoalAchieved?.let { com.gowoobro.gymspring.enums.notificationsetting.Weeklygoal.valueOf(toEnabled(it).name) } ?: setting.weeklygoal,
            personalrecord = personalRecord?.let { com.gowoobro.gymspring.enums.notificationsetting.Personalrecord.valueOf(toEnabled(it).name) } ?: setting.personalrecord,
            updateddate = LocalDateTime.now()
        )
        notificationsettingRepository.save(updated)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "message" to "알림 설정이 업데이트되었습니다",
                "data" to toSettingResponse(updated)
            )
        )
    }

    /**
     * 방해 금지 시간 설정
     */
    @PatchMapping("/settings/user/{userId}/quiet-hours")
    fun updateQuietHours(
        @PathVariable userId: Long,
        @RequestParam enabled: Boolean,
        @RequestParam(required = false) startTime: String?,
        @RequestParam(required = false) endTime: String?
    ): ResponseEntity<Map<String, Any>> {
        val setting = notificationsettingRepository.findByUser(userId).orElse(null)
            ?: return ResponseEntity.ok(mapOf("success" to false, "message" to "알림 설정을 찾을 수 없습니다"))

        val updated = setting.copy(
            quietenabled = if (enabled) com.gowoobro.gymspring.enums.notificationsetting.Quietenabled.ENABLED
                          else com.gowoobro.gymspring.enums.notificationsetting.Quietenabled.DISABLED,
            quietstart = startTime ?: "",
            quietend = endTime ?: "",
            updateddate = LocalDateTime.now()
        )
        notificationsettingRepository.save(updated)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "message" to "방해 금지 시간이 설정되었습니다",
                "data" to toSettingResponse(updated)
            )
        )
    }

    /**
     * 알림 설정 삭제
     */
    @DeleteMapping("/settings/user/{userId}")
    fun deleteUserSetting(@PathVariable userId: Long): ResponseEntity<Map<String, Any>> {
        val setting = notificationsettingRepository.findByUser(userId).orElse(null)
            ?: return ResponseEntity.ok(mapOf("success" to false, "message" to "알림 설정을 찾을 수 없습니다"))

        notificationsettingRepository.delete(setting)

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "message" to "알림 설정이 삭제되었습니다"
            )
        )
    }

    /**
     * 알림을 보낼지 확인
     */
    @GetMapping("/settings/user/{userId}/should-send")
    fun shouldSendNotification(
        @PathVariable userId: Long,
        @RequestParam type: Int
    ): ResponseEntity<Map<String, Any>> {
        val setting = notificationsettingRepository.findByUser(userId).orElse(null)

        // 설정이 없으면 기본적으로 허용
        if (setting == null) {
            return ResponseEntity.ok(
                mapOf(
                    "success" to true,
                    "shouldSend" to true
                )
            )
        }

        // 전체 알림이 꺼져있으면 불허
        if (setting.enabled.name == "DISABLED") {
            return ResponseEntity.ok(
                mapOf(
                    "success" to true,
                    "shouldSend" to false
                )
            )
        }

        // 타입별 확인
        val shouldSend = when (type) {
            1 -> setting.membershipexpiry.name == "ENABLED"
            2 -> setting.membershipnear.name == "ENABLED"
            3 -> setting.attendanceenc.name == "ENABLED"
            4 -> setting.gymannounce.name == "ENABLED"
            5 -> setting.systemnotice.name == "ENABLED"
            6 -> setting.paymentconfirm.name == "ENABLED"
            7 -> setting.pauseexpiry.name == "ENABLED"
            8 -> setting.weeklygoal.name == "ENABLED"
            9 -> setting.personalrecord.name == "ENABLED"
            else -> true
        }

        return ResponseEntity.ok(
            mapOf(
                "success" to true,
                "shouldSend" to shouldSend
            )
        )
    }

    // ==================== Helper 메서드 ====================

    private fun toHistoryResponse(history: Notificationhistory): Map<String, Any?> {
        return mapOf(
            "id" to history.id,
            "senderId" to history.senderId,
            "receiverId" to history.receiverId,
            "gymId" to history.gymId,
            "type" to history.type,
            "title" to history.title,
            "body" to history.body,
            "data" to history.data,
            "status" to history.status,
            "errorMessage" to history.errormessage,
            "sentDate" to history.sentdate.toString(),
            "date" to history.date.toString()
        )
    }

    private fun toSettingResponse(setting: Notificationsetting): Map<String, Any?> {
        return mapOf(
            "id" to setting.id,
            "userId" to setting.userId,
            "enabled" to (setting.enabled.name == "ENABLED").let { if (it) 0 else 1 },
            "membershipExpiry" to (setting.membershipexpiry.name == "ENABLED").let { if (it) 0 else 1 },
            "membershipNearExpiry" to (setting.membershipnear.name == "ENABLED").let { if (it) 0 else 1 },
            "attendanceEncourage" to (setting.attendanceenc.name == "ENABLED").let { if (it) 0 else 1 },
            "gymAnnouncement" to (setting.gymannounce.name == "ENABLED").let { if (it) 0 else 1 },
            "systemNotice" to (setting.systemnotice.name == "ENABLED").let { if (it) 0 else 1 },
            "paymentConfirm" to (setting.paymentconfirm.name == "ENABLED").let { if (it) 0 else 1 },
            "pauseExpiry" to (setting.pauseexpiry.name == "ENABLED").let { if (it) 0 else 1 },
            "weeklyGoalAchieved" to (setting.weeklygoal.name == "ENABLED").let { if (it) 0 else 1 },
            "personalRecord" to (setting.personalrecord.name == "ENABLED").let { if (it) 0 else 1 },
            "quietHoursEnabled" to (setting.quietenabled.name == "ENABLED").let { if (it) 0 else 1 },
            "quietHoursStart" to setting.quietstart?.toString(),
            "quietHoursEnd" to setting.quietend?.toString(),
            "createdDate" to setting.createddate.toString(),
            "updatedDate" to setting.updateddate.toString(),
            "date" to setting.date.toString()
        )
    }
}
