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
    @Column(name = "uhu_gym")
    val gym: Long = 0L,
    @Column(name = "uhu_usehealth")
    val usehealth: Long = 0L,
    @Column(name = "uhu_membership")
    val membership: Long = 0L,
    @Column(name = "uhu_user")
    val user: Long = 0L,
    @Column(name = "uhu_attendance")
    val attendance: Long = 0L,
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
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_usehealth", insertable = false, updatable = false)
    var usehealth: Usehealth? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_membership", insertable = false, updatable = false)
    var membership: Membership? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_user", insertable = false, updatable = false)
    var user: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "at_attendance", insertable = false, updatable = false)
    var attendance: Attendance? = null
}

data class UsehealthusageCreateRequest(
    val gym: Long = 0L,
    val usehealth: Long = 0L,
    val membership: Long = 0L,
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
    val membership: Long = 0L,
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

data class UsehealthusagePatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val usehealth: Long? = null,
    val membership: Long? = null,
    val user: Long? = null,
    val attendance: Long? = null,
    val type: Type? = null,
    val usedcount: Int? = null,
    val remainingcount: Int? = null,
    val checkintime: LocalDateTime? = null,
    val checkouttime: LocalDateTime? = null,
    val duration: Int? = null,
    val note: String? = null,
    val date: LocalDateTime? = null,
)

data class UsehealthusageExtraInfo(
    val type: String = "",

    val gym: GymResponse? = null,
    val usehealth: UsehealthResponse? = null,
    val membership: MembershipResponse? = null,
    val user: UserResponse? = null,
    val attendance: AttendanceResponse? = null,
)


data class UsehealthusageResponse(
    val id: Long,
    val gym: Long,
    val usehealth: Long,
    val membership: Long,
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
            val membershipResponse = usehealthusage.membership?.let { MembershipResponse.from(it) }
            val userResponse = usehealthusage.user?.let { UserResponse.from(it) }
            val attendanceResponse = usehealthusage.attendance?.let { AttendanceResponse.from(it) }
            return UsehealthusageResponse(
                id = usehealthusage.id,
                gym = usehealthusage.gym,
                usehealth = usehealthusage.usehealth,
                membership = usehealthusage.membership,
                user = usehealthusage.user,
                attendance = usehealthusage.attendance,
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
                    gym = gymResponse,usehealth = usehealthResponse,membership = membershipResponse,user = userResponse,attendance = attendanceResponse,)
                
            )
        }
    }
}