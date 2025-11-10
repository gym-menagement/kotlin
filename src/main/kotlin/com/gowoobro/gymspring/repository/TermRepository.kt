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
    override fun findAll(pageable: Pageable): Page<Term>


    fun findByGym(gym: Long): List<Term>

    fun findByDaytype(daytype: Long): List<Term>

    fun findByName(name: String): List<Term>

    fun findByTerm(term: Int): List<Term>

    fun findByDate(date: LocalDateTime): List<Term>
}