package com.gowoobro.gymspring.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.gowoobro.gymspring.entity.NotificationHistory
import com.gowoobro.gymspring.enums.notificationhistory.NotificationType
import com.gowoobro.gymspring.enums.notificationhistory.SendStatus
import com.gowoobro.gymspring.repository.NotificationHistoryRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FcmService(
    private val notificationHistoryRepository: NotificationHistoryRepository
) {
    private val objectMapper = jacksonObjectMapper()

    /**
     * 알림 히스토리 저장 헬퍼 함수
     */
    private fun saveNotificationHistory(
        receiverId: Long,
        title: String,
        body: String,
        data: Map<String, String>,
        type: NotificationType,
        senderId: Long? = null,
        gymId: Long? = null,
        status: SendStatus,
        errorMessage: String? = null
    ) {
        try {
            val history = NotificationHistory(
                senderId = senderId,
                receiverId = receiverId,
                gymId = gymId,
                type = type,
                title = title,
                body = body,
                data = objectMapper.writeValueAsString(data),
                status = status,
                errorMessage = errorMessage,
                sentDate = LocalDateTime.now(),
                date = LocalDateTime.now()
            )
            notificationHistoryRepository.save(history)
        } catch (e: Exception) {
            println("Failed to save notification history: ${e.message}")
        }
    }

    /**
     * 단일 디바이스에 푸시 알림 전송
     */
    fun sendNotification(
        token: String,
        title: String,
        body: String,
        data: Map<String, String> = emptyMap()
    ): String? {
        return try {
            val notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build()

            val messageBuilder = Message.builder()
                .setToken(token)
                .setNotification(notification)

            // 추가 데이터가 있으면 포함
            if (data.isNotEmpty()) {
                messageBuilder.putAllData(data)
            }

            val message = messageBuilder.build()
            val response = FirebaseMessaging.getInstance().send(message)

            println("Successfully sent message: $response")
            response
        } catch (e: Exception) {
            println("Failed to send message: ${e.message}")
            e.printStackTrace()
            null
        }
    }

    /**
     * 여러 디바이스에 푸시 알림 전송 (sendEach 사용)
     */
    fun sendMulticastNotification(
        tokens: List<String>,
        title: String,
        body: String,
        data: Map<String, String> = emptyMap(),
        userIds: List<Long> = emptyList(),
        type: NotificationType = NotificationType.GENERAL,
        senderId: Long? = null,
        gymId: Long? = null
    ): Int {
        if (tokens.isEmpty()) {
            return 0
        }

        return try {
            val notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build()

            // 각 토큰별로 Message 객체 생성
            val messages = tokens.map { token ->
                val messageBuilder = Message.builder()
                    .setToken(token)
                    .setNotification(notification)

                if (data.isNotEmpty()) {
                    messageBuilder.putAllData(data)
                }

                messageBuilder.build()
            }

            // sendEach 사용 (sendMulticast 대체)
            val response = FirebaseMessaging.getInstance().sendEach(messages)

            println("Successfully sent ${response.successCount} messages")
            println("Failed to send ${response.failureCount} messages")

            // 히스토리 저장 (userIds가 제공된 경우)
            if (userIds.isNotEmpty()) {
                userIds.forEach { userId ->
                    saveNotificationHistory(
                        receiverId = userId,
                        title = title,
                        body = body,
                        data = data,
                        type = type,
                        senderId = senderId,
                        gymId = gymId,
                        status = SendStatus.SUCCESS,
                        errorMessage = null
                    )
                }
            }

            response.successCount
        } catch (e: Exception) {
            println("Failed to send messages: ${e.message}")
            e.printStackTrace()
            0
        }
    }

    /**
     * 토픽 구독자에게 푸시 알림 전송
     */
    fun sendToTopic(
        topic: String,
        title: String,
        body: String,
        data: Map<String, String> = emptyMap()
    ): String? {
        return try {
            val notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build()

            val messageBuilder = Message.builder()
                .setTopic(topic)
                .setNotification(notification)

            if (data.isNotEmpty()) {
                messageBuilder.putAllData(data)
            }

            val message = messageBuilder.build()
            val response = FirebaseMessaging.getInstance().send(message)

            println("Successfully sent topic message: $response")
            response
        } catch (e: Exception) {
            println("Failed to send topic message: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}
