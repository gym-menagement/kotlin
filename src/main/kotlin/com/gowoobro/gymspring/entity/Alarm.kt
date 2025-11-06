package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "alarm_tb")
data class Alarm(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "al_id")
    val id: Long = 0,
    @Column(name = "al_title")
    val title: String = "",
    @Column(name = "al_content")
    val content: String = "",
    @Column(name = "al_type")
    @Enumerated(EnumType.ORDINAL)
    val type: Type,
    @Column(name = "al_status")
    @Enumerated(EnumType.ORDINAL)
    val status: Status,
    @Column(name = "al_user")
    val user: Long = 0L,
    @Column(name = "al_date")
    val date: LocalDateTime? = null,
)

data class AlarmCreateRequest(
    val title: String = "",
    val content: String = "",
    val type: Type,
    val status: Status,
    val user: Long = 0L,
    val date: LocalDateTime? = null,
)

data class AlarmUpdateRequest(
    val id: Long = 0,
    val title: String = "",
    val content: String = "",
    val type: Type,
    val status: Status,
    val user: Long = 0L,
    val date: LocalDateTime? = null,
)