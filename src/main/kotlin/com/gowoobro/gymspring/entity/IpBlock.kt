package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ipblock.Type
import com.gowoobro.gymspring.enums.ipblock.Policy
import com.gowoobro.gymspring.enums.ipblock.Use

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
    val type: Type = Type.IPV4,
    @Column(name = "ib_policy")
    val policy: Policy = Policy.ALLOW,
    @Column(name = "ib_use")
    val use: Use = Use.USE,
    @Column(name = "ib_order")
    val order: Int = 0,
    @Column(name = "ib_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class IpblockCreateRequest(
    val address: String = "",
    val type: Type = Type.IPV4,
    val policy: Policy = Policy.ALLOW,
    val use: Use = Use.USE,
    val order: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class IpblockUpdateRequest(
    val id: Long = 0,
    val address: String = "",
    val type: Type = Type.IPV4,
    val policy: Policy = Policy.ALLOW,
    val use: Use = Use.USE,
    val order: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)