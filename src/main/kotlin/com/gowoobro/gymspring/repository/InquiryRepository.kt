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

import com.gowoobro.gymspring.enums.inquiry.Type
import com.gowoobro.gymspring.enums.inquiry.Status


@Repository
interface InquiryRepository : JpaRepository<Inquiry, Long> {
    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser")
    override fun findAll(pageable: Pageable): Page<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.userId = :inquireruser")
    fun findByUserWithJoin(inquireruser: Long): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.title = :title")
    fun findByTitleWithJoin(title: String): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.content = :content")
    fun findByContentWithJoin(content: String): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.answer = :answer")
    fun findByAnswerWithJoin(answer: String): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.answeredbyId = :answeredbyuser")
    fun findByAnsweredbyWithJoin(answeredbyuser: Long): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.answereddate = :answereddate")
    fun findByAnswereddateWithJoin(answereddate: LocalDateTime): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.createddate = :createddate")
    fun findByCreateddateWithJoin(createddate: LocalDateTime): List<Inquiry>

    @Query("SELECT m FROM Inquiry m LEFT JOIN FETCH m.inquireruser LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.answeredbyuser WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Inquiry>
}