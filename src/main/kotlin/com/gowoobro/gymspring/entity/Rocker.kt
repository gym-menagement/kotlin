package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "rocker_tb")
data class Rocker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    val id: Long = 0,
    @Column(name = "r_group")
    val group: Long = 0L,
    @Column(name = "r_name")
    val name: String = "",
    @Column(name = "r_available")
    val available: Int = 0,
    @Column(name = "r_date")
    val date: LocalDateTime? = null,
)

data class RockerCreateRequest(
    val group: Long = 0L,
    val name: String = "",
    val available: Int = 0,
    val date: LocalDateTime? = null,
)

data class RockerUpdateRequest(
    val id: Long = 0,
    val group: Long = 0L,
    val name: String = "",
    val available: Int = 0,
    val date: LocalDateTime? = null,
)