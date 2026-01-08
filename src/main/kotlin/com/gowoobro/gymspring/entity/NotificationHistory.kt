package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notificationhistory.NotificationType
import com.gowoobro.gymspring.enums.notificationhistory.SendStatus

@Entity
@Table(name = "notificationhistory_tb")
data class NotificationHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nh_id")
    val id: Long = 0,
    @Column(name = "nh_sender")
    val senderId: Long? = null,  // 누가 보냈는지 (관리자 ID, null이면 시스템 자동)
    @Column(name = "nh_receiver")
    val receiverId: Long = 0L,  // 받는 사람 ID
    @Column(name = "nh_gym")
    val gymId: Long? = null,  // 어느 체육관 관련인지 (있는 경우)
    @Column(name = "nh_type")
    val type: NotificationType = NotificationType.GENERAL,  // 알림 타입
    @Column(name = "nh_title", columnDefinition = "TEXT")
    val title: String = "",
    @Column(name = "nh_body", columnDefinition = "TEXT")
    val body: String = "",
    @Column(name = "nh_data", columnDefinition = "TEXT")
    val data: String = "",  // JSON 형태로 저장
    @Column(name = "nh_status")
    val status: SendStatus = SendStatus.PENDING,  // 전송 상태
    @Column(name = "nh_errormessage", columnDefinition = "TEXT")
    val errorMessage: String? = null,
    @Column(name = "nh_sentdate")
    val sentDate: LocalDateTime = LocalDateTime.now(),
    @Column(name = "nh_date")
    val date: LocalDateTime = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nh_sender", insertable = false, updatable = false)
    var sender: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nh_receiver", insertable = false, updatable = false)
    var receiver: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nh_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class NotificationHistoryCreateRequest(
    val senderId: Long? = null,
    val receiverId: Long = 0L,
    val gymId: Long? = null,
    val type: NotificationType = NotificationType.GENERAL,
    val title: String = "",
    val body: String = "",
    val data: String = "",
    val status: SendStatus = SendStatus.PENDING,
    val errorMessage: String? = null,
    val sentDate: LocalDateTime = LocalDateTime.now(),
    val date: LocalDateTime = LocalDateTime.now(),
)

data class NotificationHistoryUpdateRequest(
    val id: Long = 0,
    val senderId: Long? = null,
    val receiverId: Long = 0L,
    val gymId: Long? = null,
    val type: NotificationType = NotificationType.GENERAL,
    val title: String = "",
    val body: String = "",
    val data: String = "",
    val status: SendStatus = SendStatus.PENDING,
    val errorMessage: String? = null,
    val sentDate: LocalDateTime = LocalDateTime.now(),
    val date: LocalDateTime = LocalDateTime.now(),
)

data class NotificationHistoryPatchRequest(
    val id: Long = 0,
    val senderId: Long? = null,
    val receiverId: Long? = null,
    val gymId: Long? = null,
    val type: NotificationType? = null,
    val title: String? = null,
    val body: String? = null,
    val data: String? = null,
    val status: SendStatus? = null,
    val errorMessage: String? = null,
    val sentDate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class NotificationHistoryExtraInfo(
    val type: String = "",
    val status: String = "",
    val sender: UserResponse? = null,
    val receiver: UserResponse? = null,
    val gym: GymResponse? = null,
)

data class NotificationHistoryResponse(
    val id: Long,
    val senderId: Long?,
    val receiverId: Long,
    val gymId: Long?,
    val type: Int,
    val title: String,
    val body: String,
    val data: String,
    val status: Int,
    val errorMessage: String?,
    val sentDate: String,
    val date: String,
    val extra: NotificationHistoryExtraInfo
) {
    companion object {
        fun from(history: NotificationHistory): NotificationHistoryResponse {
            val senderResponse = history.sender?.let { UserResponse.from(it) }
            val receiverResponse = history.receiver?.let { UserResponse.from(it) }
            val gymResponse = history.gym?.let { GymResponse.from(it) }

            return NotificationHistoryResponse(
                id = history.id,
                senderId = history.senderId,
                receiverId = history.receiverId,
                gymId = history.gymId,
                type = history.type.ordinal,
                title = history.title,
                body = history.body,
                data = history.data,
                status = history.status.ordinal,
                errorMessage = history.errorMessage,
                sentDate = history.sentDate.toString().replace("T", " "),
                date = history.date.toString().replace("T", " "),
                extra = NotificationHistoryExtraInfo(
                    type = NotificationType.getDisplayName(history.type),
                    status = SendStatus.getDisplayName(history.status),
                    sender = senderResponse,
                    receiver = receiverResponse,
                    gym = gymResponse,
                )
            )
        }
    }
}
