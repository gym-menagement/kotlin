package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.role.Roleid

@Entity
@Table(name = "role_tb")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    val id: Long = 0,

    @Column(name = "r_gym", insertable = false, updatable = false)
    val gymId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_gym")
    val gym: Gym? = null,
    @Column(name = "r_roleid")
    val roleid: Roleid = Roleid.MEMBER,
    @Column(name = "r_name")
    val name: String = "",
    @Column(name = "r_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RoleCreateRequest(
    val gym: Long = 0L,
    val roleid: Roleid = Roleid.MEMBER,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RoleUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val roleid: Roleid = Roleid.MEMBER,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RoleExtraInfo(
    val roleid: String = "",

    val gym: GymResponse? = null,
)


data class RoleResponse(
    val id: Long,
    val gym: Long,
    val roleid: Int,
    val name: String,
    val date: String?,

    val extra: RoleExtraInfo
){
    companion object {
        fun from(role: Role): RoleResponse {
            val gymResponse = role.gym?.let { GymResponse.from(it) }
            return RoleResponse(
                id = role.id,
                gym = role.gymId,
                roleid = role.roleid.ordinal,
                name = role.name,
                date = role.date?.toString()?.replace("T", " ") ?: "",

                extra = RoleExtraInfo(
                    roleid = Roleid.getDisplayName(role.roleid),
                    gym = gymResponse,)
                
            )
        }
    }
}