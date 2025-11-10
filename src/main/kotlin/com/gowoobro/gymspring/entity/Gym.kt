package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "gym_tb")
data class Gym(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g_id")
    val id: Long = 0,
    @Column(name = "g_name")
    val name: String = "",
    @Column(name = "g_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class GymCreateRequest(
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class GymUpdateRequest(
    val id: Long = 0,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)