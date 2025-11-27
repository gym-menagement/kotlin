package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ptreservation.Status

@Entity
@Table(name = "ptreservation_tb")
data class Ptreservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_id")
    val id: Long = 0,
    @Column(name = "pr_trainer")
    val trainerId: Long = 0L,
    @Column(name = "pr_member")
    val memberId: Long = 0L,
    @Column(name = "pr_gym")
    val gymId: Long = 0L,
    @Column(name = "pr_reservationdate")
    val reservationdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pr_starttime")
    val starttime: String = "",
    @Column(name = "pr_endtime")
    val endtime: String = "",
    @Column(name = "pr_duration")
    val duration: Int = 0,
    @Column(name = "pr_status")
    val status: Status = Status.RESERVED,
    @Column(name = "pr_note")
    val note: String = "",
    @Column(name = "pr_cancelreason")
    val cancelreason: String = "",
    @Column(name = "pr_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pr_updateddate")
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pr_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_trainer", insertable = false, updatable = false)
    var traineruser: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_member", insertable = false, updatable = false)
    var memberuser: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class PtreservationCreateRequest(
    val trainer: Long = 0L,
    val member: Long = 0L,
    val gym: Long = 0L,
    val reservationdate: LocalDateTime? = LocalDateTime.now(),
    val starttime: String = "",
    val endtime: String = "",
    val duration: Int = 0,
    val status: Status = Status.RESERVED,
    val note: String = "",
    val cancelreason: String = "",
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PtreservationUpdateRequest(
    val id: Long = 0,
    val trainer: Long = 0L,
    val member: Long = 0L,
    val gym: Long = 0L,
    val reservationdate: LocalDateTime? = LocalDateTime.now(),
    val starttime: String = "",
    val endtime: String = "",
    val duration: Int = 0,
    val status: Status = Status.RESERVED,
    val note: String = "",
    val cancelreason: String = "",
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PtreservationPatchRequest(
    val id: Long = 0,
    val trainer: Long? = null,
    val member: Long? = null,
    val gym: Long? = null,
    val reservationdate: LocalDateTime? = null,
    val starttime: String? = null,
    val endtime: String? = null,
    val duration: Int? = null,
    val status: Status? = null,
    val note: String? = null,
    val cancelreason: String? = null,
    val createddate: LocalDateTime? = null,
    val updateddate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class PtreservationExtraInfo(
    val status: String = "",

    val traineruser: UserResponse? = null,
    val memberuser: UserResponse? = null,
    val gym: GymResponse? = null,
)


data class PtreservationResponse(
    val id: Long,
    val trainer: Long,
    val member: Long,
    val gym: Long,
    val reservationdate: String?,
    val starttime: String,
    val endtime: String,
    val duration: Int,
    val status: Int,
    val note: String,
    val cancelreason: String,
    val createddate: String?,
    val updateddate: String?,
    val date: String?,

    val extra: PtreservationExtraInfo
){
    companion object {
        fun from(ptreservation: Ptreservation): PtreservationResponse {
            val traineruserResponse = ptreservation.traineruser?.let { UserResponse.from(it) }
            val memberuserResponse = ptreservation.memberuser?.let { UserResponse.from(it) }
            val gymResponse = ptreservation.gym?.let { GymResponse.from(it) }
            return PtreservationResponse(
                id = ptreservation.id,
                trainer = ptreservation.trainerId,
                member = ptreservation.memberId,
                gym = ptreservation.gymId,
                reservationdate = ptreservation.reservationdate?.toString()?.replace("T", " ") ?: "",
                starttime = ptreservation.starttime,
                endtime = ptreservation.endtime,
                duration = ptreservation.duration,
                status = ptreservation.status.ordinal,
                note = ptreservation.note,
                cancelreason = ptreservation.cancelreason,
                createddate = ptreservation.createddate?.toString()?.replace("T", " ") ?: "",
                updateddate = ptreservation.updateddate?.toString()?.replace("T", " ") ?: "",
                date = ptreservation.date?.toString()?.replace("T", " ") ?: "",

                extra = PtreservationExtraInfo(
                    status = Status.getDisplayName(ptreservation.status),
                    traineruser = traineruserResponse,memberuser = memberuserResponse,gym = gymResponse,)
                
            )
        }
    }
}