package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "daytype_tb")
data class Daytype(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dt_id")
    val id: Long = 0,
    @Column(name = "dt_gym")
    val gym: Long = 0L,
    @Column(name = "dt_name")
    val name: String = "",
    @Column(name = "dt_date")
    val date: LocalDateTime? = null,
)

data class DaytypeCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = null,
)

data class DaytypeUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = null,
)