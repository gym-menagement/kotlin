package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "paymenttype_tb")
data class Paymenttype(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_id")
    val id: Long = 0,
    @Column(name = "pt_gym")
    val gym: Long = 0L,
    @Column(name = "pt_name")
    val name: String = "",
    @Column(name = "pt_date")
    val date: LocalDateTime? = null,
)

data class PaymenttypeCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = null,
)

data class PaymenttypeUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = null,
)