package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "paymentform_tb")
data class Paymentform(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_id")
    val id: Long = 0,
    @Column(name = "pf_gym")
    val gym: Long = 0L,
    @Column(name = "pf_payment")
    val payment: Long = 0L,
    @Column(name = "pf_type")
    val type: Long = 0L,
    @Column(name = "pf_cost")
    val cost: Int = 0,
    @Column(name = "pf_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentformCreateRequest(
    val gym: Long = 0L,
    val payment: Long = 0L,
    val type: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentformUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val payment: Long = 0L,
    val type: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)