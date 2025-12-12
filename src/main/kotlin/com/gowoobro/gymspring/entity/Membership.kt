package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "membership_tb")
data class Membership(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    val id: Long = 0,
    @Column(name = "m_user")
    val user: Long = 0L,
    @Column(name = "m_gym")
    val gym: Long = 0L,
    @Column(name = "m_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_user", insertable = false, updatable = false)
    var user: User? = null
}

data class MembershipCreateRequest(
    val user: Long = 0L,
    val gym: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val gym: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipPatchRequest(
    val id: Long = 0,
    val user: Long? = null,
    val gym: Long? = null,
    val date: LocalDateTime? = null,
)

data class MembershipExtraInfo(

    val gym: GymResponse? = null,
    val user: UserResponse? = null,
)


data class MembershipResponse(
    val id: Long,
    val user: Long,
    val gym: Long,
    val date: String?,

    val extra: MembershipExtraInfo
){
    companion object {
        fun from(membership: Membership): MembershipResponse {
            val gymResponse = membership.gym?.let { GymResponse.from(it) }
            val userResponse = membership.user?.let { UserResponse.from(it) }
            return MembershipResponse(
                id = membership.id,
                user = membership.user,
                gym = membership.gym,
                date = membership.date?.toString()?.replace("T", " ") ?: "",

                extra = MembershipExtraInfo(
                    gym = gymResponse,user = userResponse,)
                
            )
        }
    }
}