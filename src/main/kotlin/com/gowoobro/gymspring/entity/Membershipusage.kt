package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "membershipusage_tb")
data class Membershipusage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mu_id")
    val id: Long = 0,
    @Column(name = "mu_membership")
    val membership: Long = 0L,
    @Column(name = "mu_user")
    val user: Long = 0L,
    @Column(name = "mu_type")
    val type: Int = 0,
    @Column(name = "mu_totaldays")
    val totaldays: Int = 0,
    @Column(name = "mu_useddays")
    val useddays: Int = 0,
    @Column(name = "mu_remainingdays")
    val remainingdays: Int = 0,
    @Column(name = "mu_totalcount")
    val totalcount: Int = 0,
    @Column(name = "mu_usedcount")
    val usedcount: Int = 0,
    @Column(name = "mu_remainingcount")
    val remainingcount: Int = 0,
    @Column(name = "mu_startdate")
    val startdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mu_enddate")
    val enddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mu_status")
    val status: Int = 0,
    @Column(name = "mu_pausedays")
    val pausedays: Int = 0,
    @Column(name = "mu_lastuseddate")
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mu_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipusageCreateRequest(
    val membership: Long = 0L,
    val user: Long = 0L,
    val type: Int = 0,
    val totaldays: Int = 0,
    val useddays: Int = 0,
    val remainingdays: Int = 0,
    val totalcount: Int = 0,
    val usedcount: Int = 0,
    val remainingcount: Int = 0,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Int = 0,
    val pausedays: Int = 0,
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipusageUpdateRequest(
    val id: Long = 0,
    val membership: Long = 0L,
    val user: Long = 0L,
    val type: Int = 0,
    val totaldays: Int = 0,
    val useddays: Int = 0,
    val remainingdays: Int = 0,
    val totalcount: Int = 0,
    val usedcount: Int = 0,
    val remainingcount: Int = 0,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Int = 0,
    val pausedays: Int = 0,
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)