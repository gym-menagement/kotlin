package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.repository.PushtokenRepository
import com.gowoobro.gymspring.repository.UsehealthRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class NotificationSchedulerService(
    private val usehealthRepository: UsehealthRepository,
    private val pushtokenRepository: PushtokenRepository,
    private val fcmService: FcmService
) {

    /**
     * 매일 오전 9시에 이용권 만료 알림 체크
     * - D-7, D-3, D-1, D-Day
     */
    @Scheduled(cron = "0 0 9 * * *") // 매일 오전 9시
    fun checkMembershipExpiry() {
        println("=== Checking membership expiry notifications ===")

        val today = LocalDate.now()
        val d7 = today.plusDays(7)
        val d3 = today.plusDays(3)
        val d1 = today.plusDays(1)

        // D-7 알림
        sendExpiryNotifications(d7, "이용권 만료 7일 전", "이용권이 7일 후 만료됩니다.")

        // D-3 알림
        sendExpiryNotifications(d3, "이용권 만료 3일 전", "이용권이 3일 후 만료됩니다.")

        // D-1 알림
        sendExpiryNotifications(d1, "이용권 만료 1일 전", "이용권이 내일 만료됩니다. 갱신을 고려해보세요.")

        // D-Day 알림
        sendExpiryNotifications(today, "이용권 만료", "이용권이 오늘 만료됩니다.")
    }

    private fun sendExpiryNotifications(targetDate: LocalDate, title: String, message: String) {
        try {
            // targetDate에 만료되는 이용권 조회
            val usehealths = usehealthRepository.findAll().filter {
                val endDay = it.endday?.toLocalDate()
                endDay == targetDate && it.status == com.gowoobro.gymspring.enums.usehealth.Status.USE // 사용중
            }

            usehealths.forEach { usehealth ->
                // 사용자의 FCM 토큰 조회
                val tokens = pushtokenRepository.findByuserId(usehealth.userId)
                    .mapNotNull { it.token }
                    .filter { it.isNotBlank() }

                if (tokens.isNotEmpty()) {
                    // 푸시 알림 전송
                    fcmService.sendMulticastNotification(
                        tokens = tokens,
                        title = title,
                        body = message,
                        data = mapOf(
                            "type" to "membership_expiry",
                            "usehealthId" to usehealth.id.toString(),
                            "expiryDate" to (usehealth.endday?.toString() ?: "")
                        )
                    )
                    println("Sent expiry notification to user ${usehealth.userId}")
                }
            }
        } catch (e: Exception) {
            println("Error sending expiry notifications: ${e.message}")
            e.printStackTrace()
        }
    }

    /**
     * 매일 오전 10시에 3일 이상 미출석 체크
     */
    @Scheduled(cron = "0 0 10 * * *") // 매일 오전 10시
    fun checkInactiveUsers() {
        println("=== Checking inactive users ===")

        try {
            val threeDaysAgo = LocalDateTime.now().minusDays(3)

            // 활성 이용권 중 3일 이상 미사용 조회
            val usehealths = usehealthRepository.findAll().filter {
                if (it.status != com.gowoobro.gymspring.enums.usehealth.Status.USE) return@filter false // 사용중

                val lastUsedDate = it.lastuseddate ?: return@filter false

                lastUsedDate.isBefore(threeDaysAgo)
            }

            usehealths.forEach { usehealth ->
                val tokens = pushtokenRepository.findByuserId(usehealth.userId)
                    .mapNotNull { it.token }
                    .filter { it.isNotBlank() }

                if (tokens.isNotEmpty()) {
                    val lastUsedDate = usehealth.lastuseddate!!
                    val daysSinceLastUse = java.time.temporal.ChronoUnit.DAYS.between(lastUsedDate, LocalDateTime.now())

                    fcmService.sendMulticastNotification(
                        tokens = tokens,
                        title = "운동하러 가실 시간이에요! 💪",
                        body = "${daysSinceLastUse}일째 운동을 쉬고 계세요. 오늘은 운동해보시는 건 어떨까요?",
                        data = mapOf(
                            "type" to "workout_reminder",
                            "usehealthId" to usehealth.id.toString(),
                            "daysSinceLastUse" to daysSinceLastUse.toString()
                        )
                    )
                    println("Sent inactivity reminder to user ${usehealth.userId}")
                }
            }
        } catch (e: Exception) {
            println("Error checking inactive users: ${e.message}")
            e.printStackTrace()
        }
    }
}
