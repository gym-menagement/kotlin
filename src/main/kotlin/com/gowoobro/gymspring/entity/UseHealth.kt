package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "usehealth_tb")
data class Usehealth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uh_id")
    val id: Long = 0,
    @Column(name = "uh_order")
    val order: Long = 0L,
    @Column(name = "uh_health")
    val health: Long = 0L,
    @Column(name = "uh_user")
    val user: Long = 0L,
    @Column(name = "uh_rocker")
    val rocker: Long = 0L,
    @Column(name = "uh_term")
    val term: Long = 0L,
    @Column(name = "uh_discount")
    val discount: Long = 0L,
    @Column(name = "uh_startday")
    val startday: LocalDateTime? = null,
    @Column(name = "uh_endday")
    val endday: LocalDateTime? = null,
    @Column(name = "uh_date")
    val date: LocalDateTime? = null,
)

data class UsehealthCreateRequest(
    val order: Long = 0L,
    val health: Long = 0L,
    val user: Long = 0L,
    val rocker: Long = 0L,
    val term: Long = 0L,
    val discount: Long = 0L,
    val startday: LocalDateTime? = null,
    val endday: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class UsehealthUpdateRequest(
    val id: Long = 0,
    val order: Long = 0L,
    val health: Long = 0L,
    val user: Long = 0L,
    val rocker: Long = 0L,
    val term: Long = 0L,
    val discount: Long = 0L,
    val startday: LocalDateTime? = null,
    val endday: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)