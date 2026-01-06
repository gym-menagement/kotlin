package com.gowoobro.gymspring.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FcmService {

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
     * 여러 디바이스에 푸시 알림 전송 (멀티캐스트)
     */
    fun sendMulticastNotification(
        tokens: List<String>,
        title: String,
        body: String,
        data: Map<String, String> = emptyMap()
    ): Int {
        if (tokens.isEmpty()) {
            return 0
        }

        return try {
            val notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build()

            val messageBuilder = com.google.firebase.messaging.MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(tokens)

            if (data.isNotEmpty()) {
                messageBuilder.putAllData(data)
            }

            val message = messageBuilder.build()
            val response = FirebaseMessaging.getInstance().sendMulticast(message)

            println("Successfully sent ${response.successCount} messages")
            println("Failed to send ${response.failureCount} messages")

            response.successCount
        } catch (e: Exception) {
            println("Failed to send multicast message: ${e.message}")
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
