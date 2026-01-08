package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notificationhistory.Type
import com.gowoobro.gymspring.enums.notificationhistory.Status

@Entity
@Table(name = "notificationhistory_tb")
data class Notificationhistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nh_id")
    val id: Long = 0,
    @Column(name = "nh_sender")
    val senderId: Long = 0L,
    @Column(name = "nh_receiver")
    val receiverId: Long = 0L,
    @Column(name = "nh_gym")
    val gymId: Long = 0L,
    @Column(name = "nh_type")
    val type: Type = Type.GENERAL,
    @Column(name = "nh_title")
    val title: String = "",
    @Column(name = "nh_body")
    val body: String = "",
    @Column(name = "nh_data")
    val data: String = "",
    @Column(name = "nh_status")
    val status: Status = Status.PENDING,
    @Column(name = "nh_errormessage")
    val errormessage: String = "",
    @Column(name = "nh_sentdate")
    val sentdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "nh_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nh_sender", insertable = false, updatable = false)
    var senderuser: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nh_receiver", insertable = false, updatable = false)
    var receiveruser: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nh_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class NotificationhistoryCreateRequest(
    val sender: Long = 0L,
    val receiver: Long = 0L,
    val gym: Long = 0L,
    val type: Type = Type.GENERAL,
    val title: String = "",
    val body: String = "",
    val data: String = "",
    val status: Status = Status.PENDING,
    val errormessage: String = "",
    val sentdate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class NotificationhistoryUpdateRequest(
    val id: Long = 0,
    val sender: Long = 0L,
    val receiver: Long = 0L,
    val gym: Long = 0L,
    val type: Type = Type.GENERAL,
    val title: String = "",
    val body: String = "",
    val data: String = "",
    val status: Status = Status.PENDING,
    val errormessage: String = "",
    val sentdate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class NotificationhistoryPatchRequest(
    val id: Long = 0,
    val sender: Long? = null,
    val receiver: Long? = null,
    val gym: Long? = null,
    val type: Type? = null,
    val title: String? = null,
    val body: String? = null,
    val data: String? = null,
    val status: Status? = null,
    val errormessage: String? = null,
    val sentdate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class NotificationhistoryExtraInfo(
    val type: String = "",
    val status: String = "",

    val senderuser: UserResponse? = null,
    val receiveruser: UserResponse? = null,
    val gym: GymResponse? = null,
)


data class NotificationhistoryResponse(
    val id: Long,
    val sender: Long,
    val receiver: Long,
    val gym: Long,
    val type: Int,
    val title: String,
    val body: String,
    val data: String,
    val status: Int,
    val errormessage: String,
    val sentdate: String?,
    val date: String?,

    val extra: NotificationhistoryExtraInfo
){
    companion object {
        fun from(notificationhistory: Notificationhistory): NotificationhistoryResponse {
            val senderuserResponse = notificationhistory.senderuser?.let { UserResponse.from(it) }
            val receiveruserResponse = notificationhistory.receiveruser?.let { UserResponse.from(it) }
            val gymResponse = notificationhistory.gym?.let { GymResponse.from(it) }
            return NotificationhistoryResponse(
                id = notificationhistory.id,
                sender = notificationhistory.senderId,
                receiver = notificationhistory.receiverId,
                gym = notificationhistory.gymId,
                type = notificationhistory.type.ordinal,
                title = notificationhistory.title,
                body = notificationhistory.body,
                data = notificationhistory.data,
                status = notificationhistory.status.ordinal,
                errormessage = notificationhistory.errormessage,
                sentdate = notificationhistory.sentdate?.toString()?.replace("T", " ") ?: "",
                date = notificationhistory.date?.toString()?.replace("T", " ") ?: "",

                extra = NotificationhistoryExtraInfo(
                    type = Type.getDisplayName(notificationhistory.type),status = Status.getDisplayName(notificationhistory.status),
                    senderuser = senderuserResponse,receiveruser = receiveruserResponse,gym = gymResponse,)
                
            )
        }
    }
}