package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.alarm.Type
import com.gowoobro.gymspring.enums.alarm.Status

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
    val type: Type = Type.NOTICE,
    @Column(name = "al_status")
    val status: Status = Status.SUCCESS,
    @Column(name = "al_user")
    val user: Long = 0L,
    @Column(name = "al_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AlarmCreateRequest(
    val title: String = "",
    val content: String = "",
    val type: Type = Type.NOTICE,
    val status: Status = Status.SUCCESS,
    val user: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AlarmUpdateRequest(
    val id: Long = 0,
    val title: String = "",
    val content: String = "",
    val type: Type = Type.NOTICE,
    val status: Status = Status.SUCCESS,
    val user: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)