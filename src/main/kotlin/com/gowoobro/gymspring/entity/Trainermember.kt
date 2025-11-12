package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.trainermember.Status

@Entity
@Table(name = "trainermember_tb")
data class Trainermember(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tm_id")
    val id: Long = 0,
    @Column(name = "tm_trainer")
    val trainer: Long = 0L,
    @Column(name = "tm_member")
    val member: Long = 0L,
    @Column(name = "tm_gym")
    val gym: Long = 0L,
    @Column(name = "tm_startdate")
    val startdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "tm_enddate")
    val enddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "tm_status")
    val status: Status = Status.TERMINATED,
    @Column(name = "tm_note")
    val note: String = "",
    @Column(name = "tm_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TrainermemberCreateRequest(
    val trainer: Long = 0L,
    val member: Long = 0L,
    val gym: Long = 0L,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.TERMINATED,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TrainermemberUpdateRequest(
    val id: Long = 0,
    val trainer: Long = 0L,
    val member: Long = 0L,
    val gym: Long = 0L,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.TERMINATED,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)