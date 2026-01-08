package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.NotificationHistoryResponse
import com.gowoobro.gymspring.repository.NotificationHistoryRepository
import com.gowoobro.gymspring.enums.notificationhistory.NotificationType
import com.gowoobro.gymspring.enums.notificationhistory.SendStatus
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/notification-history")
class NotificationHistoryController(
    private val notificationHistoryRepository: NotificationHistoryRepository
) {

    /**
     * 특정 사용자가 받은 알림 이력 조회
     * GET /api/notification-history/user/{userId}?page=0&size=20
     */
    @GetMapping("/user/{userId}")
    fun getUserNotificationHistory(
        @PathVariable userId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val pageable = PageRequest.of(page, size)
            val historyPage = notificationHistoryRepository.findByReceiverId(userId, pageable)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "content" to historyPage.content.map { NotificationHistoryResponse.from(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to get notification history: ${e.message}"
            ))
        }
    }

    /**
     * 특정 체육관 관련 알림 이력 조회
     * GET /api/notification-history/gym/{gymId}?page=0&size=20
     */
    @GetMapping("/gym/{gymId}")
    fun getGymNotificationHistory(
        @PathVariable gymId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val pageable = PageRequest.of(page, size)
            val historyPage = notificationHistoryRepository.findByGymId(gymId, pageable)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "content" to historyPage.content.map { NotificationHistoryResponse.from(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to get gym notification history: ${e.message}"
            ))
        }
    }

    /**
     * 특정 알림 타입별 이력 조회
     * GET /api/notification-history/type/{type}?page=0&size=20
     * type: 0=GENERAL, 1=MEMBERSHIP_EXPIRY, 2=MEMBERSHIP_NEAR_EXPIRY, 3=ATTENDANCE_ENCOURAGE, 4=GYM_ANNOUNCEMENT, 5=SYSTEM_NOTICE, 6=PAYMENT_CONFIRM, 7=TEST
     */
    @GetMapping("/type/{type}")
    fun getNotificationHistoryByType(
        @PathVariable type: Int,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val notificationType = NotificationType.values()[type]
            val pageable = PageRequest.of(page, size)
            val historyPage = notificationHistoryRepository.findByType(notificationType, pageable)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "content" to historyPage.content.map { NotificationHistoryResponse.from(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages,
                "type" to NotificationType.getDisplayName(notificationType)
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to get notification history by type: ${e.message}"
            ))
        }
    }

    /**
     * 특정 사용자의 특정 타입 알림 이력 조회
     * GET /api/notification-history/user/{userId}/type/{type}?page=0&size=20
     */
    @GetMapping("/user/{userId}/type/{type}")
    fun getUserNotificationHistoryByType(
        @PathVariable userId: Long,
        @PathVariable type: Int,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val notificationType = NotificationType.values()[type]
            val pageable = PageRequest.of(page, size)
            val historyPage = notificationHistoryRepository.findByReceiverIdAndType(userId, notificationType, pageable)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "content" to historyPage.content.map { NotificationHistoryResponse.from(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages,
                "userId" to userId,
                "type" to NotificationType.getDisplayName(notificationType)
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to get user notification history by type: ${e.message}"
            ))
        }
    }

    /**
     * 기간별 알림 이력 조회
     * GET /api/notification-history/date-range?startDate=2024-01-01T00:00:00&endDate=2024-12-31T23:59:59&page=0&size=20
     */
    @GetMapping("/date-range")
    fun getNotificationHistoryByDateRange(
        @RequestParam startDate: String,
        @RequestParam endDate: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val start = LocalDateTime.parse(startDate)
            val end = LocalDateTime.parse(endDate)
            val pageable = PageRequest.of(page, size)
            val historyPage = notificationHistoryRepository.findBySentDateBetween(start, end, pageable)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "content" to historyPage.content.map { NotificationHistoryResponse.from(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages,
                "startDate" to startDate,
                "endDate" to endDate
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to get notification history by date range: ${e.message}"
            ))
        }
    }

    /**
     * 전송 상태별 이력 조회
     * GET /api/notification-history/status/{status}?page=0&size=20
     * status: 0=PENDING, 1=SUCCESS, 2=FAILED
     */
    @GetMapping("/status/{status}")
    fun getNotificationHistoryByStatus(
        @PathVariable status: Int,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val sendStatus = SendStatus.values()[status]
            val pageable = PageRequest.of(page, size)
            val historyPage = notificationHistoryRepository.findByStatus(sendStatus, pageable)

            ResponseEntity.ok(mapOf(
                "success" to true,
                "content" to historyPage.content.map { NotificationHistoryResponse.from(it) },
                "page" to page,
                "size" to size,
                "totalElements" to historyPage.totalElements,
                "totalPages" to historyPage.totalPages,
                "status" to SendStatus.getDisplayName(sendStatus)
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to get notification history by status: ${e.message}"
            ))
        }
    }

    /**
     * 특정 알림 상세 조회
     * GET /api/notification-history/{id}
     */
    @GetMapping("/{id}")
    fun getNotificationHistoryById(
        @PathVariable id: Long
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val history = notificationHistoryRepository.findById(id)

            if (history.isPresent) {
                ResponseEntity.ok(mapOf(
                    "success" to true,
                    "data" to NotificationHistoryResponse.from(history.get())
                ))
            } else {
                ResponseEntity.ok(mapOf(
                    "success" to false,
                    "message" to "Notification history not found"
                ))
            }
        } catch (e: Exception) {
            ResponseEntity.ok(mapOf(
                "success" to false,
                "message" to "Failed to get notification history: ${e.message}"
            ))
        }
    }
}
