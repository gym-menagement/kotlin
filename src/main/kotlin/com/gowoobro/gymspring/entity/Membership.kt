package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.membership.Sex

@Entity
@Table(name = "membership_tb")
data class Membership(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    val id: Long = 0,
    @Column(name = "m_gym")
    val gym: Long = 0L,
    @Column(name = "m_user")
    val user: Long = 0L,
    @Column(name = "m_name")
    val name: String = "",
    @Column(name = "m_sex")
    val sex: Sex = Sex.MALE,
    @Column(name = "m_birth")
    val birth: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "m_phonenum")
    val phonenum: String = "",
    @Column(name = "m_address")
    val address: String = "",
    @Column(name = "m_image")
    val image: String = "",
    @Column(name = "m_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipCreateRequest(
    val gym: Long = 0L,
    val user: Long = 0L,
    val name: String = "",
    val sex: Sex = Sex.MALE,
    val birth: LocalDateTime? = LocalDateTime.now(),
    val phonenum: String = "",
    val address: String = "",
    val image: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val user: Long = 0L,
    val name: String = "",
    val sex: Sex = Sex.MALE,
    val birth: LocalDateTime? = LocalDateTime.now(),
    val phonenum: String = "",
    val address: String = "",
    val image: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipExtraInfo(
    val sex: String = "",
)


data class MembershipResponse(
    val id: Long,
    val gym: Long,
    val user: Long,
    val name: String,
    val sex: Int,
    val birth: String?,
    val phonenum: String,
    val address: String,
    val image: String,
    val date: String?,

    val extra: MembershipExtraInfo
){
    companion object {
        fun from(membership: Membership): MembershipResponse {
            return MembershipResponse(
                id = membership.id,
                gym = membership.gym,
                user = membership.user,
                name = membership.name,
                sex = membership.sex.ordinal,
                birth = membership.birth?.toString()?.replace("T", " ") ?: "",
                phonenum = membership.phonenum,
                address = membership.address,
                image = membership.image,
                date = membership.date?.toString()?.replace("T", " ") ?: "",
                extra = MembershipExtraInfo(
                    sex = Sex.getDisplayName(membership.sex),
                )
            )
        }
    }
}