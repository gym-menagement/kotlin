package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.usehealthusage.Type

@Entity
@Table(name = "usehealthusage_tb")
data class Usehealthusage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uhu_id")
    val id: Long = 0,

    @Column(name = "uhu_gym", insertable = false, updatable = false)
    val gymId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uhu_gym")
    val gym: Gym? = null,

    @Column(name = "uhu_usehealth", insertable = false, updatable = false)
    val usehealthId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uhu_usehealth")
    val usehealth: Usehealth? = null,

    @Column(name = "uhu_user", insertable = false, updatable = false)
    val userId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uhu_user")
    val user: User? = null,

    @Column(name = "uhu_attendance", insertable = false, updatable = false)
    val attendanceId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uhu_attendance")
    val attendance: Attendance? = null,
    @Column(name = "uhu_type")
    val type: Type = Type.ENTRY,
    @Column(name = "uhu_usedcount")
    val usedcount: Int = 0,
    @Column(name = "uhu_remainingcount")
    val remainingcount: Int = 0,
    @Column(name = "uhu_checkintime")
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uhu_checkouttime")
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uhu_duration")
    val duration: Int = 0,
    @Column(name = "uhu_note")
    val note: String = "",
    @Column(name = "uhu_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthusageCreateRequest(
    val gym: Long = 0L,
    val usehealth: Long = 0L,
    val user: Long = 0L,
    val attendance: Long = 0L,
    val type: Type = Type.ENTRY,
    val usedcount: Int = 0,
    val remainingcount: Int = 0,
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    val duration: Int = 0,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthusageUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val usehealth: Long = 0L,
    val user: Long = 0L,
    val attendance: Long = 0L,
    val type: Type = Type.ENTRY,
    val usedcount: Int = 0,
    val remainingcount: Int = 0,
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    val duration: Int = 0,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthusageExtraInfo(
    val type: String = "",

    val gym: GymResponse? = null,
    val usehealth: UsehealthResponse? = null,
    val user: UserResponse? = null,
    val attendance: AttendanceResponse? = null,
)


data class UsehealthusageResponse(
    val id: Long,
    val gym: Long,
    val usehealth: Long,
    val user: Long,
    val attendance: Long,
    val type: Int,
    val usedcount: Int,
    val remainingcount: Int,
    val checkintime: String?,
    val checkouttime: String?,
    val duration: Int,
    val note: String,
    val date: String?,

    val extra: UsehealthusageExtraInfo
){
    companion object {
        fun from(usehealthusage: Usehealthusage): UsehealthusageResponse {
            val gymResponse = usehealthusage.gym?.let { GymResponse.from(it) }
            val usehealthResponse = usehealthusage.usehealth?.let { UsehealthResponse.from(it) }
            val userResponse = usehealthusage.user?.let { UserResponse.from(it) }
            val attendanceResponse = usehealthusage.attendance?.let { AttendanceResponse.from(it) }
            return UsehealthusageResponse(
                id = usehealthusage.id,
                gym = usehealthusage.gymId,
                usehealth = usehealthusage.usehealthId,
                user = usehealthusage.userId,
                attendance = usehealthusage.attendanceId,
                type = usehealthusage.type.ordinal,
                usedcount = usehealthusage.usedcount,
                remainingcount = usehealthusage.remainingcount,
                checkintime = usehealthusage.checkintime?.toString()?.replace("T", " ") ?: "",
                checkouttime = usehealthusage.checkouttime?.toString()?.replace("T", " ") ?: "",
                duration = usehealthusage.duration,
                note = usehealthusage.note,
                date = usehealthusage.date?.toString()?.replace("T", " ") ?: "",

                extra = UsehealthusageExtraInfo(
                    type = Type.getDisplayName(usehealthusage.type),
                    gym = gymResponse,usehealth = usehealthResponse,user = userResponse,attendance = attendanceResponse,)
                
            )
        }
    }
}