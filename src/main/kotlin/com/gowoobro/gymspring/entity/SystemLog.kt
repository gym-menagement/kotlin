package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "systemlog_tb")
data class Systemlog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_id")
    val id: Long = 0,
    @Column(name = "sl_type")
    val type: Type,
    @Column(name = "sl_content")
    val content: String = "",
    @Column(name = "sl_result")
    val result: Int = 0,
    @Column(name = "sl_date")
    val date: LocalDateTime? = null,
)

data class SystemlogCreateRequest(
    val type: Type,
    val content: String = "",
    val result: Int = 0,
    val date: LocalDateTime? = null,
)

data class SystemlogUpdateRequest(
    val id: Long = 0,
    val type: Type,
    val content: String = "",
    val result: Int = 0,
    val date: LocalDateTime? = null,
)