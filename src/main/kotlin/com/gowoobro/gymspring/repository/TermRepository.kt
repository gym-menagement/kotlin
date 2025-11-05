package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Term
import com.gowoobro.gymspring.entity.TermCreateRequest
import com.gowoobro.gymspring.entity.TermUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TermRepository : JpaRepository<Term, Long> {
    override fun findAll(pageable: Pageable): Page<Term>

    override fun findById(id: String): List<Term>

    override fun findByGym(gym: String): List<Term>

    override fun findByDaytype(daytype: String): List<Term>

    override fun findByName(name: String): List<Term>

    override fun findByTerm(term: String): List<Term>

    override fun findByDate(date: String): List<Term>
}