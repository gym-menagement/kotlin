package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "payment_tb")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    val id: Long = 0,
    @Column(name = "p_gym")
    val gym: Long = 0L,
    @Column(name = "p_order")
    val order: Long = 0L,
    @Column(name = "p_membership")
    val membership: Long = 0L,
    @Column(name = "p_cost")
    val cost: Int = 0,
    @Column(name = "p_date")
    val date: LocalDateTime? = null,
)

data class PaymentCreateRequest(
    val gym: Long = 0L,
    val order: Long = 0L,
    val membership: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = null,
)

data class PaymentUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val order: Long = 0L,
    val membership: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = null,
)