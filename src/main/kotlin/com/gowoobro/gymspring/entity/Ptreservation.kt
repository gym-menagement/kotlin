package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ptreservation.Status

@Entity
@Table(name = "ptreservation_tb")
data class Ptreservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_id")
    val id: Long = 0,
    @Column(name = "pr_trainer")
    val trainer: Long = 0L,
    @Column(name = "pr_member")
    val member: Long = 0L,
    @Column(name = "pr_gym")
    val gym: Long = 0L,
    @Column(name = "pr_reservationdate")
    val reservationdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pr_starttime")
    val starttime: String = "",
    @Column(name = "pr_endtime")
    val endtime: String = "",
    @Column(name = "pr_duration")
    val duration: Int = 0,
    @Column(name = "pr_status")
    val status: Status = Status.RESERVED,
    @Column(name = "pr_note")
    val note: String = "",
    @Column(name = "pr_cancelreason")
    val cancelreason: String = "",
    @Column(name = "pr_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pr_updateddate")
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pr_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PtreservationCreateRequest(
    val trainer: Long = 0L,
    val member: Long = 0L,
    val gym: Long = 0L,
    val reservationdate: LocalDateTime? = LocalDateTime.now(),
    val starttime: String = "",
    val endtime: String = "",
    val duration: Int = 0,
    val status: Status = Status.RESERVED,
    val note: String = "",
    val cancelreason: String = "",
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PtreservationUpdateRequest(
    val id: Long = 0,
    val trainer: Long = 0L,
    val member: Long = 0L,
    val gym: Long = 0L,
    val reservationdate: LocalDateTime? = LocalDateTime.now(),
    val starttime: String = "",
    val endtime: String = "",
    val duration: Int = 0,
    val status: Status = Status.RESERVED,
    val note: String = "",
    val cancelreason: String = "",
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)