package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "appversion_tb")
data class Appversion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "av_id")
    val id: Long = 0,
    @Column(name = "av_platform")
    val platform: String = "",
    @Column(name = "av_version")
    val version: String = "",
    @Column(name = "av_minversion")
    val minversion: String = "",
    @Column(name = "av_forceupdate")
    val forceupdate: Int = 0,
    @Column(name = "av_updatemessage")
    val updatemessage: String = "",
    @Column(name = "av_downloadurl")
    val downloadurl: String = "",
    @Column(name = "av_status")
    val status: Int = 0,
    @Column(name = "av_releasedate")
    val releasedate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "av_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
)

data class AppversionCreateRequest(
    val platform: String = "",
    val version: String = "",
    val minversion: String = "",
    val forceupdate: Int = 0,
    val updatemessage: String = "",
    val downloadurl: String = "",
    val status: Int = 0,
    val releasedate: LocalDateTime? = LocalDateTime.now(),
    val createddate: LocalDateTime? = LocalDateTime.now(),
)

data class AppversionUpdateRequest(
    val id: Long = 0,
    val platform: String = "",
    val version: String = "",
    val minversion: String = "",
    val forceupdate: Int = 0,
    val updatemessage: String = "",
    val downloadurl: String = "",
    val status: Int = 0,
    val releasedate: LocalDateTime? = LocalDateTime.now(),
    val createddate: LocalDateTime? = LocalDateTime.now(),
)