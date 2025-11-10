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
    val startday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "s_endday")
    val endday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "s_count")
    val count: Int = 0,
    @Column(name = "s_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class StopCreateRequest(
    val usehelth: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val count: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class StopUpdateRequest(
    val id: Long = 0,
    val usehelth: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val count: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)