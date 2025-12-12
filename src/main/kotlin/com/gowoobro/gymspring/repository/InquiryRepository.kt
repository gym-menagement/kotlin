package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Inquiry
import com.gowoobro.gymspring.entity.InquiryCreateRequest
import com.gowoobro.gymspring.entity.InquiryUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.inquiry.Type
import com.gowoobro.gymspring.enums.inquiry.Status



@Repository
interface InquiryRepository : JpaRepository<Inquiry, Long> {
    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    override fun findAll(pageable: Pageable): Page<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    override fun findById(id: Long): java.util.Optional<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByUser(user: Long): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByGym(gym: Long): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByType(type: Type): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByTitle(title: String): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByContent(content: String): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByStatus(status: Status): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByAnswer(answer: String): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByAnsweredby(answeredby: Long): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByAnswereddate(answereddate: LocalDateTime): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByCreateddate(createddate: LocalDateTime): List<Inquiry>

    @EntityGraph(attributePaths = [
        "inquireruser",
        "gym",
        "answeredbyuser"
    ])
    fun findByDate(date: LocalDateTime): List<Inquiry>
}
