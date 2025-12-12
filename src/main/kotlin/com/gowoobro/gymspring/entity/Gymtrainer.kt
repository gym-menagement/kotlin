package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.gymtrainer.Status

@Entity
@Table(name = "gymtrainer_tb")
data class Gymtrainer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gt_id")
    val id: Long = 0,
    @Column(name = "gt_gym")
    val gym: Long = 0L,
    @Column(name = "gt_trainer")
    val trainer: Long = 0L,
    @Column(name = "gt_startdate")
    val startdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "gt_enddate")
    val enddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "gt_status")
    val status: Status = Status.TERMINATED,
    @Column(name = "gt_position")
    val position: String = "",
    @Column(name = "gt_note")
    val note: String = "",
    @Column(name = "gt_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_trainer", insertable = false, updatable = false)
    var traineruser: User? = null
}

data class GymtrainerCreateRequest(
    val gym: Long = 0L,
    val trainer: Long = 0L,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.TERMINATED,
    val position: String = "",
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class GymtrainerUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val trainer: Long = 0L,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.TERMINATED,
    val position: String = "",
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class GymtrainerPatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val trainer: Long? = null,
    val startdate: LocalDateTime? = null,
    val enddate: LocalDateTime? = null,
    val status: Status? = null,
    val position: String? = null,
    val note: String? = null,
    val date: LocalDateTime? = null,
)

data class GymtrainerExtraInfo(
    val status: String = "",

    val gym: GymResponse? = null,
    val traineruser: UserResponse? = null,
)


data class GymtrainerResponse(
    val id: Long,
    val gym: Long,
    val trainer: Long,
    val startdate: String?,
    val enddate: String?,
    val status: Int,
    val position: String,
    val note: String,
    val date: String?,

    val extra: GymtrainerExtraInfo
){
    companion object {
        fun from(gymtrainer: Gymtrainer): GymtrainerResponse {
            val gymResponse = gymtrainer.gym?.let { GymResponse.from(it) }
            val traineruserResponse = gymtrainer.traineruser?.let { UserResponse.from(it) }
            return GymtrainerResponse(
                id = gymtrainer.id,
                gym = gymtrainer.gym,
                trainer = gymtrainer.trainer,
                startdate = gymtrainer.startdate?.toString()?.replace("T", " ") ?: "",
                enddate = gymtrainer.enddate?.toString()?.replace("T", " ") ?: "",
                status = gymtrainer.status.ordinal,
                position = gymtrainer.position,
                note = gymtrainer.note,
                date = gymtrainer.date?.toString()?.replace("T", " ") ?: "",

                extra = GymtrainerExtraInfo(
                    status = Status.getDisplayName(gymtrainer.status),
                    gym = gymResponse,traineruser = traineruserResponse,)
                
            )
        }
    }
}