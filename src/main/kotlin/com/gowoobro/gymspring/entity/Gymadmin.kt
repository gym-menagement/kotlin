package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.entity.GymAdminLevel
import com.gowoobro.gymspring.entity.Status

@Entity
@Table(name = "gymadmin_tb")
data class Gymadmin(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ga_id")
    val id: Long = 0,
    @Column(name = "ga_gym")
    val gymId: Long = 0L,
    @Column(name = "ga_user")
    val userId: Long = 0L,
    @Column(name = "ga_level")
    val level: GymAdminLevel = GymAdminLevel.MANAGER,
    @Column(name = "ga_status")
    val status: Status = Status.ACTIVE,
    @Column(name = "ga_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ga_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ga_user", insertable = false, updatable = false)
    var user: User? = null
}

data class GymadminCreateRequest(
    val gym: Long = 0L,
    val user: Long = 0L,
    val level: GymAdminLevel = GymAdminLevel.MANAGER,
    val status: Status = Status.ACTIVE,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class GymadminUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val user: Long = 0L,
    val level: GymAdminLevel = GymAdminLevel.MANAGER,
    val status: Status = Status.ACTIVE,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class GymadminPatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val user: Long? = null,
    val level: GymAdminLevel? = null,
    val status: Status? = null,
    val date: LocalDateTime? = null,
)

data class GymadminExtraInfo(
    val level: String = "",
    val status: String = "",

    val gym: GymResponse? = null,
    val user: UserResponse? = null,
)


data class GymadminResponse(
    val id: Long,
    val gym: Long,
    val user: Long,
    val level: Int,
    val status: Int,
    val date: String?,

    val extra: GymadminExtraInfo
){
    companion object {
        fun from(gymadmin: Gymadmin): GymadminResponse {
            val gymResponse = gymadmin.gym?.let { GymResponse.from(it) }
            val userResponse = gymadmin.user?.let { UserResponse.from(it) }
            return GymadminResponse(
                id = gymadmin.id,
                gym = gymadmin.gymId,
                user = gymadmin.userId,
                level = gymadmin.level.ordinal,
                status = gymadmin.status.ordinal,
                date = gymadmin.date?.toString()?.replace("T", " ") ?: "",

                extra = GymadminExtraInfo(
                    level = gymadmin.level.name,
                    status = gymadmin.status.name,
                    gym = gymResponse,user = userResponse,)
                
            )
        }
    }
}
