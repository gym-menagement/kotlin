package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "stop_tb")
data class Stop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    val id: Long = 0,
    @Column(name = "s_usehelth")
    val usehelth: Long = 0L,
    @Column(name = "s_startday")
    val startday: LocalDateTime? = null,
    @Column(name = "s_endday")
    val endday: LocalDateTime? = null,
    @Column(name = "s_count")
    val count: Int = 0,
    @Column(name = "s_date")
    val date: LocalDateTime? = null,
)

data class StopCreateRequest(
    val usehelth: Long = 0L,
    val startday: LocalDateTime? = null,
    val endday: LocalDateTime? = null,
    val count: Int = 0,
    val date: LocalDateTime? = null,
)

data class StopUpdateRequest(
    val id: Long = 0,
    val usehelth: Long = 0L,
    val startday: LocalDateTime? = null,
    val endday: LocalDateTime? = null,
    val count: Int = 0,
    val date: LocalDateTime? = null,
)