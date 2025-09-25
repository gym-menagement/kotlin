package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "token_tb")
data class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "to_id")
    val id: Long = 0,
    @Column(name = "to_user")
    val user: Long = 0L,
    @Column(name = "to_token")
    val token: String = "",
    @Column(name = "to_status")
    val status: Int = 0,
    @Column(name = "to_date")
    val date: LocalDateTime? = null,
)

data class TokenCreateRequest(
    val user: Long = 0L,
    val token: String = "",
    val status: Int = 0,
    val date: LocalDateTime? = null,
)

data class TokenUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val token: String = "",
    val status: Int = 0,
    val date: LocalDateTime? = null,
)