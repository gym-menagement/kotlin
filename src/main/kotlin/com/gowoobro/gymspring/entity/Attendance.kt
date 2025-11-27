package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.attendance.Type
import com.gowoobro.gymspring.enums.attendance.Method
import com.gowoobro.gymspring.enums.attendance.Status

@Entity
@Table(name = "attendance_tb")
data class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    val id: Long = 0,
    @Column(name = "at_user")
    val userId: Long = 0L,
    @Column(name = "at_usehealth")
    val usehealthId: Long = 0L,
    @Column(name = "at_gym")
    val gymId: Long = 0L,
    @Column(name = "at_type")
    val type: Type = Type.ENTRY,
    @Column(name = "at_method")
    val method: Method = Method.QR_CODE,
    @Column(name = "at_checkintime")
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "at_checkouttime")
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "at_duration")
    val duration: Int = 0,
    @Column(name = "at_status")
    val status: Status = Status.NORMAL,
    @Column(name = "at_note")
    val note: String = "",
    @Column(name = "at_ip")
    val ip: String = "",
    @Column(name = "at_device")
    val device: String = "",
    @Column(name = "at_createdby")
    val createdby: Long = 0L,
    @Column(name = "at_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "at_user", insertable = false, updatable = false)
    var user: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "at_usehealth", insertable = false, updatable = false)
    var usehealth: Usehealth? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "at_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class AttendanceCreateRequest(
    val user: Long = 0L,
    val usehealth: Long = 0L,
    val gym: Long = 0L,
    val type: Type = Type.ENTRY,
    val method: Method = Method.QR_CODE,
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    val duration: Int = 0,
    val status: Status = Status.NORMAL,
    val note: String = "",
    val ip: String = "",
    val device: String = "",
    val createdby: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AttendanceUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val usehealth: Long = 0L,
    val gym: Long = 0L,
    val type: Type = Type.ENTRY,
    val method: Method = Method.QR_CODE,
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    val duration: Int = 0,
    val status: Status = Status.NORMAL,
    val note: String = "",
    val ip: String = "",
    val device: String = "",
    val createdby: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AttendanceExtraInfo(
    val type: String = "",
    val method: String = "",
    val status: String = "",

    val user: UserResponse? = null,
    val usehealth: UsehealthResponse? = null,
    val gym: GymResponse? = null,
)


data class AttendanceResponse(
    val id: Long,
    val user: Long,
    val usehealth: Long,
    val gym: Long,
    val type: Int,
    val method: Int,
    val checkintime: String?,
    val checkouttime: String?,
    val duration: Int,
    val status: Int,
    val note: String,
    val ip: String,
    val device: String,
    val createdby: Long,
    val date: String?,

    val extra: AttendanceExtraInfo
){
    companion object {
        fun from(attendance: Attendance): AttendanceResponse {
            val userResponse = attendance.user?.let { UserResponse.from(it) }
            val usehealthResponse = attendance.usehealth?.let { UsehealthResponse.from(it) }
            val gymResponse = attendance.gym?.let { GymResponse.from(it) }
            return AttendanceResponse(
                id = attendance.id,
                user = attendance.userId,
                usehealth = attendance.usehealthId,
                gym = attendance.gymId,
                type = attendance.type.ordinal,
                method = attendance.method.ordinal,
                checkintime = attendance.checkintime?.toString()?.replace("T", " ") ?: "",
                checkouttime = attendance.checkouttime?.toString()?.replace("T", " ") ?: "",
                duration = attendance.duration,
                status = attendance.status.ordinal,
                note = attendance.note,
                ip = attendance.ip,
                device = attendance.device,
                createdby = attendance.createdby,
                date = attendance.date?.toString()?.replace("T", " ") ?: "",

                extra = AttendanceExtraInfo(
                    type = Type.getDisplayName(attendance.type),method = Method.getDisplayName(attendance.method),status = Status.getDisplayName(attendance.status),
                    user = userResponse,usehealth = usehealthResponse,gym = gymResponse,)
                
            )
        }
    }
}