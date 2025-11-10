package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "inquiry_tb")
data class Inquiry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iq_id")
    val id: Long = 0,
    @Column(name = "iq_user")
    val user: Long = 0L,
    @Column(name = "iq_gym")
    val gym: Long = 0L,
    @Column(name = "iq_type")
    val type: Int = 0,
    @Column(name = "iq_title")
    val title: String = "",
    @Column(name = "iq_content")
    val content: String = "",
    @Column(name = "iq_status")
    val status: Int = 0,
    @Column(name = "iq_answer")
    val answer: String = "",
    @Column(name = "iq_answeredby")
    val answeredby: Long = 0L,
    @Column(name = "iq_answereddate")
    val answereddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "iq_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
)

data class InquiryCreateRequest(
    val user: Long = 0L,
    val gym: Long = 0L,
    val type: Int = 0,
    val title: String = "",
    val content: String = "",
    val status: Int = 0,
    val answer: String = "",
    val answeredby: Long = 0L,
    val answereddate: LocalDateTime? = LocalDateTime.now(),
    val createddate: LocalDateTime? = LocalDateTime.now(),
)

data class InquiryUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val gym: Long = 0L,
    val type: Int = 0,
    val title: String = "",
    val content: String = "",
    val status: Int = 0,
    val answer: String = "",
    val answeredby: Long = 0L,
    val answereddate: LocalDateTime? = LocalDateTime.now(),
    val createddate: LocalDateTime? = LocalDateTime.now(),
)