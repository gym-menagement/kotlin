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
    @Column(name = "r_gym")
    val gym: Long = 0L,
    @Column(name = "r_roleid")
    val roleid: Roleid = Roleid.MEMBER,
    @Column(name = "r_name")
    val name: String = "",
    @Column(name = "r_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

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

data class RolePatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val roleid: Roleid? = null,
    val name: String? = null,
    val date: LocalDateTime? = null,
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
                gym = role.gym,
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