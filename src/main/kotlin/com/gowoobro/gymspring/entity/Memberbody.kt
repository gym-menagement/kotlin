package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal

@Entity
@Table(name = "memberbody_tb")
data class Memberbody(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mb_id")
    val id: Long = 0,
    @Column(name = "mb_user")
    val user: Long = 0L,
    @Column(name = "mb_height")
    val height: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_weight")
    val weight: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_bodyfat")
    val bodyfat: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_musclemass")
    val musclemass: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_bmi")
    val bmi: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_skeletalmuscle")
    val skeletalmuscle: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_bodywater")
    val bodywater: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_chest")
    val chest: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_waist")
    val waist: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_hip")
    val hip: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_arm")
    val arm: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_thigh")
    val thigh: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_note")
    val note: String = "",
    @Column(name = "mb_measureddate")
    val measureddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mb_measuredby")
    val measuredby: Long = 0L,
    @Column(name = "mb_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MemberbodyCreateRequest(
    val user: Long = 0L,
    val height: BigDecimal = BigDecimal.ZERO,
    val weight: BigDecimal = BigDecimal.ZERO,
    val bodyfat: BigDecimal = BigDecimal.ZERO,
    val musclemass: BigDecimal = BigDecimal.ZERO,
    val bmi: BigDecimal = BigDecimal.ZERO,
    val skeletalmuscle: BigDecimal = BigDecimal.ZERO,
    val bodywater: BigDecimal = BigDecimal.ZERO,
    val chest: BigDecimal = BigDecimal.ZERO,
    val waist: BigDecimal = BigDecimal.ZERO,
    val hip: BigDecimal = BigDecimal.ZERO,
    val arm: BigDecimal = BigDecimal.ZERO,
    val thigh: BigDecimal = BigDecimal.ZERO,
    val note: String = "",
    val measureddate: LocalDateTime? = LocalDateTime.now(),
    val measuredby: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MemberbodyUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val height: BigDecimal = BigDecimal.ZERO,
    val weight: BigDecimal = BigDecimal.ZERO,
    val bodyfat: BigDecimal = BigDecimal.ZERO,
    val musclemass: BigDecimal = BigDecimal.ZERO,
    val bmi: BigDecimal = BigDecimal.ZERO,
    val skeletalmuscle: BigDecimal = BigDecimal.ZERO,
    val bodywater: BigDecimal = BigDecimal.ZERO,
    val chest: BigDecimal = BigDecimal.ZERO,
    val waist: BigDecimal = BigDecimal.ZERO,
    val hip: BigDecimal = BigDecimal.ZERO,
    val arm: BigDecimal = BigDecimal.ZERO,
    val thigh: BigDecimal = BigDecimal.ZERO,
    val note: String = "",
    val measureddate: LocalDateTime? = LocalDateTime.now(),
    val measuredby: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)