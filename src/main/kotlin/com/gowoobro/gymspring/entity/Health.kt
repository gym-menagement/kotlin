package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "health_tb")
data class Health(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id")
    val id: Long = 0,
    @Column(name = "h_category")
    val category: Long = 0L,
    @Column(name = "h_term")
    val term: Long = 0L,
    @Column(name = "h_name")
    val name: String = "",
    @Column(name = "h_count")
    val count: Int = 0,
    @Column(name = "h_cost")
    val cost: Int = 0,
    @Column(name = "h_discount")
    val discount: Long = 0L,
    @Column(name = "h_costdiscount")
    val costdiscount: Int = 0,
    @Column(name = "h_content")
    val content: String = "",
    @Column(name = "h_date")
    val date: LocalDateTime? = null,
)

data class HealthCreateRequest(
    val category: Long = 0L,
    val term: Long = 0L,
    val name: String = "",
    val count: Int = 0,
    val cost: Int = 0,
    val discount: Long = 0L,
    val costdiscount: Int = 0,
    val content: String = "",
    val date: LocalDateTime? = null,
)

data class HealthUpdateRequest(
    val id: Long = 0,
    val category: Long = 0L,
    val term: Long = 0L,
    val name: String = "",
    val count: Int = 0,
    val cost: Int = 0,
    val discount: Long = 0L,
    val costdiscount: Int = 0,
    val content: String = "",
    val date: LocalDateTime? = null,
)