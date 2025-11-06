package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "ipblock_tb")
data class Ipblock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ib_id")
    val id: Long = 0,
    @Column(name = "ib_address")
    val address: String = "",
    @Column(name = "ib_type")
    @Enumerated(EnumType.ORDINAL)
    val type: Type,
    @Column(name = "ib_policy")
    @Enumerated(EnumType.ORDINAL)
    val policy: Policy,
    @Column(name = "ib_use")
    @Enumerated(EnumType.ORDINAL)
    val use: Use,
    @Column(name = "ib_order")
    val order: Int = 0,
    @Column(name = "ib_date")
    val date: LocalDateTime? = null,
)

data class IpblockCreateRequest(
    val address: String = "",
    val type: Type,
    val policy: Policy,
    val use: Use,
    val order: Int = 0,
    val date: LocalDateTime? = null,
)

data class IpblockUpdateRequest(
    val id: Long = 0,
    val address: String = "",
    val type: Type,
    val policy: Policy,
    val use: Use,
    val order: Int = 0,
    val date: LocalDateTime? = null,
)