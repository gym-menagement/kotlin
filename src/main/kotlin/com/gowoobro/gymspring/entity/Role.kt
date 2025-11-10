package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


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
    val role: Int = 0,
    @Column(name = "r_name")
    val name: String = "",
    @Column(name = "r_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RoleCreateRequest(
    val gym: Long = 0L,
    val role: Int = 0,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RoleUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val role: Int = 0,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)