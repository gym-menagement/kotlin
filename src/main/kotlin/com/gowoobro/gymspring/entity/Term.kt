package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "term_tb")
data class Term(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    val id: Long = 0,
    @Column(name = "t_gym")
    val gym: Long = 0L,
    @Column(name = "t_daytype")
    val daytype: Long = 0L,
    @Column(name = "t_name")
    val name: String = "",
    @Column(name = "t_term")
    val term: Int = 0,
    @Column(name = "t_date")
    val date: LocalDateTime? = null,
)

data class TermCreateRequest(
    val gym: Long = 0L,
    val daytype: Long = 0L,
    val name: String = "",
    val term: Int = 0,
    val date: LocalDateTime? = null,
)

data class TermUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val daytype: Long = 0L,
    val name: String = "",
    val term: Int = 0,
    val date: LocalDateTime? = null,
)