package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Inquiry
import com.gowoobro.gymspring.entity.InquiryCreateRequest
import com.gowoobro.gymspring.entity.InquiryUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface InquiryRepository : JpaRepository<Inquiry, Long> {
    override fun findAll(pageable: Pageable): Page<Inquiry>

    fun findByUser(user: Long): List<Inquiry>

    fun findByGym(gym: Long): List<Inquiry>

    fun findByType(type: Int): List<Inquiry>

    fun findByTitle(title: String): List<Inquiry>

    fun findByContent(content: String): List<Inquiry>

    fun findByStatus(status: Int): List<Inquiry>

    fun findByAnswer(answer: String): List<Inquiry>

    fun findByAnsweredby(answeredby: Long): List<Inquiry>

    fun findByAnswereddate(answereddate: LocalDateTime): List<Inquiry>

    fun findByCreateddate(createddate: LocalDateTime): List<Inquiry>
}