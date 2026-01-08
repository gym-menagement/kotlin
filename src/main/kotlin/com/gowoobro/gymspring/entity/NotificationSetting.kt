package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notificationsetting.Enabled
import com.gowoobro.gymspring.enums.notificationsetting.Membershipexpiry
import com.gowoobro.gymspring.enums.notificationsetting.Membershipnear
import com.gowoobro.gymspring.enums.notificationsetting.Attendanceenc
import com.gowoobro.gymspring.enums.notificationsetting.Gymannounce
import com.gowoobro.gymspring.enums.notificationsetting.Systemnotice
import com.gowoobro.gymspring.enums.notificationsetting.Paymentconfirm
import com.gowoobro.gymspring.enums.notificationsetting.Pauseexpiry
import com.gowoobro.gymspring.enums.notificationsetting.Weeklygoal
import com.gowoobro.gymspring.enums.notificationsetting.Personalrecord
import com.gowoobro.gymspring.enums.notificationsetting.Quietenabled

@Entity
@Table(name = "notificationsetting_tb")
data class Notificationsetting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ns_id")
    val id: Long = 0,
    @Column(name = "ns_user")
    val userId: Long = 0L,
    @Column(name = "ns_enabled")
    val enabled: Enabled = Enabled.ENABLED,
    @Column(name = "ns_membershipexpiry")
    val membershipexpiry: Membershipexpiry = Membershipexpiry.ENABLED,
    @Column(name = "ns_membershipnear")
    val membershipnear: Membershipnear = Membershipnear.ENABLED,
    @Column(name = "ns_attendanceenc")
    val attendanceenc: Attendanceenc = Attendanceenc.ENABLED,
    @Column(name = "ns_gymannounce")
    val gymannounce: Gymannounce = Gymannounce.ENABLED,
    @Column(name = "ns_systemnotice")
    val systemnotice: Systemnotice = Systemnotice.ENABLED,
    @Column(name = "ns_paymentconfirm")
    val paymentconfirm: Paymentconfirm = Paymentconfirm.ENABLED,
    @Column(name = "ns_pauseexpiry")
    val pauseexpiry: Pauseexpiry = Pauseexpiry.ENABLED,
    @Column(name = "ns_weeklygoal")
    val weeklygoal: Weeklygoal = Weeklygoal.ENABLED,
    @Column(name = "ns_personalrecord")
    val personalrecord: Personalrecord = Personalrecord.ENABLED,
    @Column(name = "ns_quietenabled")
    val quietenabled: Quietenabled = Quietenabled.ENABLED,
    @Column(name = "ns_quietstart")
    val quietstart: String = "",
    @Column(name = "ns_quietend")
    val quietend: String = "",
    @Column(name = "ns_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "ns_updateddate")
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "ns_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ns_user", insertable = false, updatable = false)
    var user: User? = null
}

data class NotificationsettingCreateRequest(
    val user: Long = 0L,
    val enabled: Enabled = Enabled.ENABLED,
    val membershipexpiry: Membershipexpiry = Membershipexpiry.ENABLED,
    val membershipnear: Membershipnear = Membershipnear.ENABLED,
    val attendanceenc: Attendanceenc = Attendanceenc.ENABLED,
    val gymannounce: Gymannounce = Gymannounce.ENABLED,
    val systemnotice: Systemnotice = Systemnotice.ENABLED,
    val paymentconfirm: Paymentconfirm = Paymentconfirm.ENABLED,
    val pauseexpiry: Pauseexpiry = Pauseexpiry.ENABLED,
    val weeklygoal: Weeklygoal = Weeklygoal.ENABLED,
    val personalrecord: Personalrecord = Personalrecord.ENABLED,
    val quietenabled: Quietenabled = Quietenabled.ENABLED,
    val quietstart: String = "",
    val quietend: String = "",
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class NotificationsettingUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val enabled: Enabled = Enabled.ENABLED,
    val membershipexpiry: Membershipexpiry = Membershipexpiry.ENABLED,
    val membershipnear: Membershipnear = Membershipnear.ENABLED,
    val attendanceenc: Attendanceenc = Attendanceenc.ENABLED,
    val gymannounce: Gymannounce = Gymannounce.ENABLED,
    val systemnotice: Systemnotice = Systemnotice.ENABLED,
    val paymentconfirm: Paymentconfirm = Paymentconfirm.ENABLED,
    val pauseexpiry: Pauseexpiry = Pauseexpiry.ENABLED,
    val weeklygoal: Weeklygoal = Weeklygoal.ENABLED,
    val personalrecord: Personalrecord = Personalrecord.ENABLED,
    val quietenabled: Quietenabled = Quietenabled.ENABLED,
    val quietstart: String = "",
    val quietend: String = "",
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class NotificationsettingPatchRequest(
    val id: Long = 0,
    val user: Long? = null,
    val enabled: Enabled? = null,
    val membershipexpiry: Membershipexpiry? = null,
    val membershipnear: Membershipnear? = null,
    val attendanceenc: Attendanceenc? = null,
    val gymannounce: Gymannounce? = null,
    val systemnotice: Systemnotice? = null,
    val paymentconfirm: Paymentconfirm? = null,
    val pauseexpiry: Pauseexpiry? = null,
    val weeklygoal: Weeklygoal? = null,
    val personalrecord: Personalrecord? = null,
    val quietenabled: Quietenabled? = null,
    val quietstart: String? = null,
    val quietend: String? = null,
    val createddate: LocalDateTime? = null,
    val updateddate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class NotificationsettingExtraInfo(
    val enabled: String = "",
    val membershipexpiry: String = "",
    val membershipnear: String = "",
    val attendanceenc: String = "",
    val gymannounce: String = "",
    val systemnotice: String = "",
    val paymentconfirm: String = "",
    val pauseexpiry: String = "",
    val weeklygoal: String = "",
    val personalrecord: String = "",
    val quietenabled: String = "",

    val user: UserResponse? = null,
)


data class NotificationsettingResponse(
    val id: Long,
    val user: Long,
    val enabled: Int,
    val membershipexpiry: Int,
    val membershipnear: Int,
    val attendanceenc: Int,
    val gymannounce: Int,
    val systemnotice: Int,
    val paymentconfirm: Int,
    val pauseexpiry: Int,
    val weeklygoal: Int,
    val personalrecord: Int,
    val quietenabled: Int,
    val quietstart: String,
    val quietend: String,
    val createddate: String?,
    val updateddate: String?,
    val date: String?,

    val extra: NotificationsettingExtraInfo
){
    companion object {
        fun from(notificationsetting: Notificationsetting): NotificationsettingResponse {
            val userResponse = notificationsetting.user?.let { UserResponse.from(it) }
            return NotificationsettingResponse(
                id = notificationsetting.id,
                user = notificationsetting.userId,
                enabled = notificationsetting.enabled.ordinal,
                membershipexpiry = notificationsetting.membershipexpiry.ordinal,
                membershipnear = notificationsetting.membershipnear.ordinal,
                attendanceenc = notificationsetting.attendanceenc.ordinal,
                gymannounce = notificationsetting.gymannounce.ordinal,
                systemnotice = notificationsetting.systemnotice.ordinal,
                paymentconfirm = notificationsetting.paymentconfirm.ordinal,
                pauseexpiry = notificationsetting.pauseexpiry.ordinal,
                weeklygoal = notificationsetting.weeklygoal.ordinal,
                personalrecord = notificationsetting.personalrecord.ordinal,
                quietenabled = notificationsetting.quietenabled.ordinal,
                quietstart = notificationsetting.quietstart,
                quietend = notificationsetting.quietend,
                createddate = notificationsetting.createddate?.toString()?.replace("T", " ") ?: "",
                updateddate = notificationsetting.updateddate?.toString()?.replace("T", " ") ?: "",
                date = notificationsetting.date?.toString()?.replace("T", " ") ?: "",

                extra = NotificationsettingExtraInfo(
                    enabled = Enabled.getDisplayName(notificationsetting.enabled),membershipexpiry = Membershipexpiry.getDisplayName(notificationsetting.membershipexpiry),membershipnear = Membershipnear.getDisplayName(notificationsetting.membershipnear),attendanceenc = Attendanceenc.getDisplayName(notificationsetting.attendanceenc),gymannounce = Gymannounce.getDisplayName(notificationsetting.gymannounce),systemnotice = Systemnotice.getDisplayName(notificationsetting.systemnotice),paymentconfirm = Paymentconfirm.getDisplayName(notificationsetting.paymentconfirm),pauseexpiry = Pauseexpiry.getDisplayName(notificationsetting.pauseexpiry),weeklygoal = Weeklygoal.getDisplayName(notificationsetting.weeklygoal),personalrecord = Personalrecord.getDisplayName(notificationsetting.personalrecord),quietenabled = Quietenabled.getDisplayName(notificationsetting.quietenabled),
                    user = userResponse,)
                
            )
        }
    }
}