package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.repository.PushtokenRepository
import com.gowoobro.gymspring.service.FcmService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notifications")
class NotificationController(
    private val fcmService: FcmService,
    private val pushtokenRepository: PushtokenRepository,
    private val notificationSchedulerService: com.gowoobro.gymspring.service.NotificationSchedulerService
) {

    /**
     * 테스트용 푸시 알림 전송
     */
    @PostMapping("/test")
    fun sendTestNotification(
        @RequestParam userId: Long,
        @RequestParam(required = false, defaultValue = "테스트 알림") title: String,
        @RequestParam(required = false, defaultValue = "이것은 테스트 알림입니다") body: String
    ): ResponseEntity<Map<String, Any>> {
        return try {
            // 사용자의 FCM 토큰 조회
            val tokens = pushtokenRepository.findByuserId(userId)
                .mapNotNull { it.token }
                .filter { it.isNotBlank() }

            if (tokens.isEmpty()) {
                return ResponseEntity.ok(mapOf(
                    "success" to false,
                    "message" to "No FCM tokens found for user $userId"
                ))
            }

            // 푸시 알림 전송
            val successCount = fcmService.sendMulticastNotification(
                tokens = tokens,
                title = title,
                body = body,
                data = mapOf("type" to "test")
            )

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Sent notification to $successCount devices",
                "userId" to userId,
                "tokenCount" to tokens.size,
                "successCount" to successCount
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to send notification: ${e.message}"
            ))
        }
    }

    /**
     * 특정 토큰으로 직접 푸시 알림 전송
     */
    @PostMapping("/send")
    fun sendNotification(
        @RequestBody request: SendNotificationRequest
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val response = fcmService.sendNotification(
                token = request.token,
                title = request.title,
                body = request.body,
                data = request.data ?: emptyMap()
            )

            ResponseEntity.ok(mapOf<String, Any>(
                "success" to (response != null),
                "message" to if (response != null) "Notification sent successfully" else "Failed to send notification",
                "messageId" to (response ?: "")
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to send notification: ${e.message}"
            ))
        }
    }

    /**
     * 스케줄러 수동 실행 - 이용권 만료 체크
     */
    @PostMapping("/test/check-expiry")
    fun testCheckMembershipExpiry(): ResponseEntity<Map<String, Any>> {
        return try {
            notificationSchedulerService.checkMembershipExpiry()
            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Membership expiry check executed successfully"
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to check membership expiry: ${e.message}"
            ))
        }
    }

    /**
     * 스케줄러 수동 실행 - 3일 미출석 체크
     */
    @PostMapping("/test/check-inactive")
    fun testCheckInactiveUsers(): ResponseEntity<Map<String, Any>> {
        return try {
            notificationSchedulerService.checkInactiveUsers()
            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Inactive users check executed successfully"
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to check inactive users: ${e.message}"
            ))
        }
    }
}

data class SendNotificationRequest(
    val token: String,
    val title: String,
    val body: String,
    val data: Map<String, String>? = null
)
