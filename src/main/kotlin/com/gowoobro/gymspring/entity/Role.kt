package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.role.Role as RoleEnum

@Entity
@Table(name = "role_tb")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    val id: Long = 0,
    @Column(name = "r_gym")
    val gym: Long = 0L,
    @Column(name = "r_role")
    val role: RoleEnum = RoleEnum.MEMBER,
    @Column(name = "r_name")
    val name: String = "",
    @Column(name = "r_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RoleCreateRequest(
    val gym: Long = 0L,
    val role: RoleEnum = RoleEnum.MEMBER,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RoleUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val role: RoleEnum = RoleEnum.MEMBER,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)