package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Term
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface TermRepository : JpaRepository<Term, Long> {
    
    fun findByGym(gym: Long): List<Term>
    
    fun findByDaytype(daytype: Long): List<Term>
    
    fun findByNameContaining(name: String): List<Term>
    
    fun findByTerm(term: Int): List<Term>
    
    override fun findAll(pageable: Pageable): Page<Term>
}