package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "rockergroup_tb")
data class Rockergroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rg_id")
    val id: Long = 0,
    @Column(name = "rg_gym")
    val gym: Long = 0L,
    @Column(name = "rg_name")
    val name: String = "",
    @Column(name = "rg_date")
    val date: LocalDateTime? = null,
)

data class RockergroupCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = null,
)

data class RockergroupUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = null,
)