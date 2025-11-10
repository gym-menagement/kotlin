package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "healthcategory_tb")
data class Healthcategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hc_id")
    val id: Long = 0,
    @Column(name = "hc_gym")
    val gym: Long = 0L,
    @Column(name = "hc_name")
    val name: String = "",
    @Column(name = "hc_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class HealthcategoryCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class HealthcategoryUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)