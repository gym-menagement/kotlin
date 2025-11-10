package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "attendance_tb")
data class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    val id: Long = 0,
    @Column(name = "at_user")
    val user: Long = 0L,
    @Column(name = "at_membership")
    val membership: Long = 0L,
    @Column(name = "at_gym")
    val gym: Long = 0L,
    @Column(name = "at_type")
    val type: Int = 0,
    @Column(name = "at_method")
    val method: Int = 0,
    @Column(name = "at_checkintime")
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "at_checkouttime")
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "at_duration")
    val duration: Int = 0,
    @Column(name = "at_status")
    val status: Int = 0,
    @Column(name = "at_note")
    val note: String = "",
    @Column(name = "at_ip")
    val ip: String = "",
    @Column(name = "at_device")
    val device: String = "",
    @Column(name = "at_createdby")
    val createdby: Long = 0L,
    @Column(name = "at_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AttendanceCreateRequest(
    val user: Long = 0L,
    val membership: Long = 0L,
    val gym: Long = 0L,
    val type: Int = 0,
    val method: Int = 0,
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    val duration: Int = 0,
    val status: Int = 0,
    val note: String = "",
    val ip: String = "",
    val device: String = "",
    val createdby: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AttendanceUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val membership: Long = 0L,
    val gym: Long = 0L,
    val type: Int = 0,
    val method: Int = 0,
    val checkintime: LocalDateTime? = LocalDateTime.now(),
    val checkouttime: LocalDateTime? = LocalDateTime.now(),
    val duration: Int = 0,
    val status: Int = 0,
    val note: String = "",
    val ip: String = "",
    val device: String = "",
    val createdby: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)