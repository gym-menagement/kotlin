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
    @Column(name = "m_gym")
    val gym: Long = 0L,
    @Column(name = "m_user")
    val user: Long = 0L,
    @Column(name = "m_name")
    val name: String = "",
    @Column(name = "m_sex")
    val sex: Int = 0,
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
    val sex: Int = 0,
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
    val sex: Int = 0,
    val birth: LocalDateTime? = LocalDateTime.now(),
    val phonenum: String = "",
    val address: String = "",
    val image: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)