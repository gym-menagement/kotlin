package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Term
import com.gowoobro.gymspring.entity.TermCreateRequest
import com.gowoobro.gymspring.entity.TermUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface TermRepository : JpaRepository<Term, Long> {
    @Query("SELECT m FROM Term m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.daytype")
    override fun findAll(pageable: Pageable): Page<Term>

    @Query("SELECT m FROM Term m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.daytype WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Term>

    @Query("SELECT m FROM Term m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.daytype WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Term>

    @Query("SELECT m FROM Term m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.daytype WHERE m.daytypeId = :daytype")
    fun findByDaytypeWithJoin(daytype: Long): List<Term>

    @Query("SELECT m FROM Term m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.daytype WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Term>

    @Query("SELECT m FROM Term m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.daytype WHERE m.term = :term")
    fun findByTermWithJoin(term: Int): List<Term>

    @Query("SELECT m FROM Term m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.daytype WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Term>
}