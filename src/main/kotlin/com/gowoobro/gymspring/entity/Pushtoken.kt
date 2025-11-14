package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.pushtoken.Isactive

@Entity
@Table(name = "pushtoken_tb")
data class Pushtoken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_id")
    val id: Long = 0,

    @Column(name = "pt_user", insertable = false, updatable = false)
    val userId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_user")
    val user: User? = null,
    @Column(name = "pt_token")
    val token: String = "",
    @Column(name = "pt_devicetype")
    val devicetype: String = "",
    @Column(name = "pt_deviceid")
    val deviceid: String = "",
    @Column(name = "pt_appversion")
    val appversion: String = "",
    @Column(name = "pt_isactive")
    val isactive: Isactive = Isactive.INACTIVE,
    @Column(name = "pt_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pt_updateddate")
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pt_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PushtokenCreateRequest(
    val user: Long = 0L,
    val token: String = "",
    val devicetype: String = "",
    val deviceid: String = "",
    val appversion: String = "",
    val isactive: Isactive = Isactive.INACTIVE,
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PushtokenUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val token: String = "",
    val devicetype: String = "",
    val deviceid: String = "",
    val appversion: String = "",
    val isactive: Isactive = Isactive.INACTIVE,
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PushtokenExtraInfo(
    val isactive: String = "",

    val user: UserResponse? = null,
)


data class PushtokenResponse(
    val id: Long,
    val user: Long,
    val token: String,
    val devicetype: String,
    val deviceid: String,
    val appversion: String,
    val isactive: Int,
    val createddate: String?,
    val updateddate: String?,
    val date: String?,

    val extra: PushtokenExtraInfo
){
    companion object {
        fun from(pushtoken: Pushtoken): PushtokenResponse {
            val userResponse = pushtoken.user?.let { UserResponse.from(it) }
            return PushtokenResponse(
                id = pushtoken.id,
                user = pushtoken.userId,
                token = pushtoken.token,
                devicetype = pushtoken.devicetype,
                deviceid = pushtoken.deviceid,
                appversion = pushtoken.appversion,
                isactive = pushtoken.isactive.ordinal,
                createddate = pushtoken.createddate?.toString()?.replace("T", " ") ?: "",
                updateddate = pushtoken.updateddate?.toString()?.replace("T", " ") ?: "",
                date = pushtoken.date?.toString()?.replace("T", " ") ?: "",

                extra = PushtokenExtraInfo(
                    isactive = Isactive.getDisplayName(pushtoken.isactive),
                    user = userResponse,)
                
            )
        }
    }
}