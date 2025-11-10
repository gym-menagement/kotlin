package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.pushtoken.Isactive

@Entity
@Table(name = "pushtoken_tb")
data class Pushtoken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_id")
    val id: Long = 0,
    @Column(name = "pt_user")
    val user: Long = 0L,
    @Column(name = "pt_token")
    val token: String = "",
    @Column(name = "pt_devicetype")
    val devicetype: String = "",
    @Column(name = "pt_deviceid")
    val deviceid: String = "",
    @Column(name = "pt_appversion")
    val appversion: String = "",
    @Column(name = "pt_isactive")
    val isactive: Isactive = Isactive.0,
    @Column(name = "pt_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "pt_updateddate")
    val updateddate: LocalDateTime? = LocalDateTime.now(),
)

data class PushtokenCreateRequest(
    val user: Long = 0L,
    val token: String = "",
    val devicetype: String = "",
    val deviceid: String = "",
    val appversion: String = "",
    val isactive: Isactive = Isactive.0,
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
)

data class PushtokenUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val token: String = "",
    val devicetype: String = "",
    val deviceid: String = "",
    val appversion: String = "",
    val isactive: Isactive = Isactive.0,
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
)