package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.trainermember.Status

@Entity
@Table(name = "trainermember_tb")
data class Trainermember(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tm_id")
    val id: Long = 0,
    @Column(name = "tm_trainer")
    val trainerId: Long = 0L,
    @Column(name = "tm_member")
    val memberId: Long = 0L,
    @Column(name = "tm_gym")
    val gymId: Long = 0L,
    @Column(name = "tm_startdate")
    val startdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "tm_enddate")
    val enddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "tm_status")
    val status: Status = Status.TERMINATED,
    @Column(name = "tm_note")
    val note: String = "",
    @Column(name = "tm_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tm_trainer", insertable = false, updatable = false)
    var traineruser: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tm_member", insertable = false, updatable = false)
    var memberuser: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tm_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class TrainermemberCreateRequest(
    val trainer: Long = 0L,
    val member: Long = 0L,
    val gym: Long = 0L,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.TERMINATED,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TrainermemberUpdateRequest(
    val id: Long = 0,
    val trainer: Long = 0L,
    val member: Long = 0L,
    val gym: Long = 0L,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.TERMINATED,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TrainermemberPatchRequest(
    val id: Long = 0,
    val trainer: Long? = null,
    val member: Long? = null,
    val gym: Long? = null,
    val startdate: LocalDateTime? = null,
    val enddate: LocalDateTime? = null,
    val status: Status? = null,
    val note: String? = null,
    val date: LocalDateTime? = null,
)

data class TrainermemberExtraInfo(
    val status: String = "",

    val traineruser: UserResponse? = null,
    val memberuser: UserResponse? = null,
    val gym: GymResponse? = null,
)


data class TrainermemberResponse(
    val id: Long,
    val trainer: Long,
    val member: Long,
    val gym: Long,
    val startdate: String?,
    val enddate: String?,
    val status: Int,
    val note: String,
    val date: String?,

    val extra: TrainermemberExtraInfo
){
    companion object {
        fun from(trainermember: Trainermember): TrainermemberResponse {
            val traineruserResponse = trainermember.traineruser?.let { UserResponse.from(it) }
            val memberuserResponse = trainermember.memberuser?.let { UserResponse.from(it) }
            val gymResponse = trainermember.gym?.let { GymResponse.from(it) }
            return TrainermemberResponse(
                id = trainermember.id,
                trainer = trainermember.trainerId,
                member = trainermember.memberId,
                gym = trainermember.gymId,
                startdate = trainermember.startdate?.toString()?.replace("T", " ") ?: "",
                enddate = trainermember.enddate?.toString()?.replace("T", " ") ?: "",
                status = trainermember.status.ordinal,
                note = trainermember.note,
                date = trainermember.date?.toString()?.replace("T", " ") ?: "",

                extra = TrainermemberExtraInfo(
                    status = Status.getDisplayName(trainermember.status),
                    traineruser = traineruserResponse,memberuser = memberuserResponse,gym = gymResponse,)
                
            )
        }
    }
}