package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal

@Entity
@Table(name = "workoutlog_tb")
data class Workoutlog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wl_id")
    val id: Long = 0,
    @Column(name = "wl_user")
    val user: Long = 0L,
    @Column(name = "wl_attendance")
    val attendance: Long = 0L,
    @Column(name = "wl_health")
    val health: Long = 0L,
    @Column(name = "wl_exercisename")
    val exercisename: String = "",
    @Column(name = "wl_sets")
    val sets: Int = 0,
    @Column(name = "wl_reps")
    val reps: Int = 0,
    @Column(name = "wl_weight")
    val weight: BigDecimal = BigDecimal.ZERO,
    @Column(name = "wl_duration")
    val duration: Int = 0,
    @Column(name = "wl_calories")
    val calories: Int = 0,
    @Column(name = "wl_note")
    val note: String = "",
    @Column(name = "wl_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class WorkoutlogCreateRequest(
    val user: Long = 0L,
    val attendance: Long = 0L,
    val health: Long = 0L,
    val exercisename: String = "",
    val sets: Int = 0,
    val reps: Int = 0,
    val weight: BigDecimal = BigDecimal.ZERO,
    val duration: Int = 0,
    val calories: Int = 0,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class WorkoutlogUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val attendance: Long = 0L,
    val health: Long = 0L,
    val exercisename: String = "",
    val sets: Int = 0,
    val reps: Int = 0,
    val weight: BigDecimal = BigDecimal.ZERO,
    val duration: Int = 0,
    val calories: Int = 0,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)