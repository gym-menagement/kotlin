package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Term
import com.gowoobro.gymspring.entity.TermCreateRequest
import com.gowoobro.gymspring.entity.TermUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface TermRepository : JpaRepository<Term, Long> {
    @EntityGraph(attributePaths = ["gym", "daytype"])
    override fun findAll(pageable: Pageable): Page<Term>

    @EntityGraph(attributePaths = ["gym", "daytype"])
    override fun findById(id: Long): java.util.Optional<Term>

    @EntityGraph(attributePaths = ["gym", "daytype"])
    fun findBygymId(gym: Long): List<Term>

    @EntityGraph(attributePaths = ["gym", "daytype"])
    fun findBydaytypeId(daytype: Long): List<Term>

    @EntityGraph(attributePaths = ["gym", "daytype"])
    fun findByName(name: String): List<Term>

    @EntityGraph(attributePaths = ["gym", "daytype"])
    fun findByTerm(term: Int): List<Term>

    @EntityGraph(attributePaths = ["gym", "daytype"])
    fun findByDate(date: LocalDateTime): List<Term>
}
