package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.systemlog.Type

@Entity
@Table(name = "systemlog_tb")
data class Systemlog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_id")
    val id: Long = 0,
    @Column(name = "sl_type")
    val type: Type = Type.LOGIN,
    @Column(name = "sl_content")
    val content: String = "",
    @Column(name = "sl_result")
    val result: Int = 0,
    @Column(name = "sl_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class SystemlogCreateRequest(
    val type: Type = Type.LOGIN,
    val content: String = "",
    val result: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class SystemlogUpdateRequest(
    val id: Long = 0,
    val type: Type = Type.LOGIN,
    val content: String = "",
    val result: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)