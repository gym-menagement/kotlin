package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.repository.PushtokenRepository
import com.gowoobro.gymspring.service.FcmService
import com.gowoobro.gymspring.enums.notificationhistory.Type
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notifications")
class NotificationController(
    private val fcmService: FcmService,
    private val pushtokenRepository: PushtokenRepository,
    private val notificationSchedulerService: com.gowoobro.gymspring.service.NotificationSchedulerService,
    private val membershipRepository: com.gowoobro.gymspring.repository.MembershipRepository
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
                data = mapOf("type" to "test"),
                userIds = listOf(userId),
                type = Type.GENERAL
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
     * 여러 사용자에게 푸시 알림 전송
     * POST /api/notifications/send-multiple?userIds=1,2,3&title=제목&body=내용
     */
    @PostMapping("/send-multiple")
    fun sendMultipleNotification(
        @RequestParam userIds: List<Long>,
        @RequestParam(required = false, defaultValue = "알림") title: String,
        @RequestParam(required = false, defaultValue = "새로운 알림이 도착했습니다") body: String,
        @RequestParam(required = false) data: Map<String, String>?
    ): ResponseEntity<Map<String, Any>> {
        return try {
            // 모든 사용자의 FCM 토큰 조회
            val allTokens = mutableListOf<String>()
            val userTokenMap = mutableMapOf<Long, Int>()

            userIds.forEach { userId ->
                val tokens = pushtokenRepository.findByuserId(userId)
                    .filter { it.isactive == com.gowoobro.gymspring.enums.pushtoken.Isactive.ACTIVE }
                    .mapNotNull { it.token }
                    .filter { it.isNotBlank() }

                userTokenMap[userId] = tokens.size
                allTokens.addAll(tokens)
            }

            if (allTokens.isEmpty()) {
                return ResponseEntity.ok(mapOf(
                    "success" to false,
                    "message" to "No active FCM tokens found for users: $userIds"
                ))
            }

            // 푸시 알림 전송
            val successCount = fcmService.sendMulticastNotification(
                tokens = allTokens,
                title = title,
                body = body,
                data = data ?: mapOf("type" to "notification"),
                userIds = userIds,
                type = Type.GENERAL
            )

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Sent notification to $successCount devices",
                "userIds" to userIds,
                "userCount" to userIds.size,
                "totalTokens" to allTokens.size,
                "successCount" to successCount,
                "userTokenMap" to userTokenMap
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to send notifications: ${e.message}"
            ))
        }
    }

    /**
     * 특정 체육관의 모든 회원에게 푸시 알림 전송
     * POST /api/notifications/send-to-gym?gymId=1&title=공지&body=내용
     */
    @PostMapping("/send-to-gym")
    fun sendToGymMembers(
        @RequestParam gymId: Long,
        @RequestParam(required = false, defaultValue = "헬스장 공지") title: String,
        @RequestParam(required = false, defaultValue = "새로운 공지사항이 있습니다") body: String,
        @RequestParam(required = false) data: Map<String, String>?
    ): ResponseEntity<Map<String, Any>> {
        return try {
            // 해당 체육관의 모든 멤버십 조회
            val memberships = membershipRepository.findBygymId(gymId)

            if (memberships.isEmpty()) {
                return ResponseEntity.ok(mapOf(
                    "success" to false,
                    "message" to "No memberships found for gym $gymId"
                ))
            }

            // 멤버십에서 사용자 ID 추출
            val userIds = memberships.mapNotNull { it.userId }.distinct()

            // 모든 사용자의 FCM 토큰 조회
            val allTokens = mutableListOf<String>()
            val userTokenMap = mutableMapOf<Long, Int>()

            userIds.forEach { userId ->
                val tokens = pushtokenRepository.findByuserId(userId)
                    .filter { it.isactive == com.gowoobro.gymspring.enums.pushtoken.Isactive.ACTIVE }
                    .mapNotNull { it.token }
                    .filter { it.isNotBlank() }

                if (tokens.isNotEmpty()) {
                    userTokenMap[userId] = tokens.size
                    allTokens.addAll(tokens)
                }
            }

            if (allTokens.isEmpty()) {
                return ResponseEntity.ok(mapOf(
                    "success" to false,
                    "message" to "No active FCM tokens found for gym members",
                    "gymId" to gymId,
                    "membershipCount" to memberships.size,
                    "userCount" to userIds.size
                ))
            }

            // 푸시 알림 전송
            val successCount = fcmService.sendMulticastNotification(
                tokens = allTokens,
                title = title,
                body = body,
                data = data ?: mapOf("type" to "gym_notification", "gymId" to gymId.toString()),
                userIds = userIds,
                type = Type.GYM_ANNOUNCEMENT,
                gymId = gymId
            )

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Sent notification to $successCount devices in gym $gymId",
                "gymId" to gymId,
                "membershipCount" to memberships.size,
                "userCount" to userIds.size,
                "totalTokens" to allTokens.size,
                "successCount" to successCount,
                "userTokenMap" to userTokenMap
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to send notification to gym members: ${e.message}"
            ))
        }
    }

    /**
     * 모든 활성 사용자에게 푸시 알림 전송 (공지사항용)
     * POST /api/notifications/broadcast?title=공지&body=내용
     */
    @PostMapping("/broadcast")
    fun broadcastNotification(
        @RequestParam(required = false, defaultValue = "공지사항") title: String,
        @RequestParam(required = false, defaultValue = "새로운 공지사항이 있습니다") body: String,
        @RequestParam(required = false) data: Map<String, String>?
    ): ResponseEntity<Map<String, Any>> {
        return try {
            // 모든 활성 FCM 토큰 조회
            val activeTokens = pushtokenRepository.findByIsactive(
                com.gowoobro.gymspring.enums.pushtoken.Isactive.ACTIVE
            )

            val allTokens = activeTokens
                .mapNotNull { it.token }
                .filter { it.isNotBlank() }
                .distinct() // 중복 제거

            val userIds = activeTokens
                .map { it.userId }
                .distinct()

            if (allTokens.isEmpty()) {
                return ResponseEntity.ok(mapOf(
                    "success" to false,
                    "message" to "No active FCM tokens found"
                ))
            }

            // 푸시 알림 전송
            val successCount = fcmService.sendMulticastNotification(
                tokens = allTokens,
                title = title,
                body = body,
                data = data ?: mapOf("type" to "broadcast"),
                userIds = userIds,
                type = Type.SYSTEM_NOTICE
            )

            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Broadcast notification sent to $successCount devices",
                "totalTokens" to allTokens.size,
                "successCount" to successCount
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to broadcast notification: ${e.message}"
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
