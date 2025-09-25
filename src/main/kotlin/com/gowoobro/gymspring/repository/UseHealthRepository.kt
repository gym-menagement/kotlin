package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Usehealth
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface UsehealthRepository : JpaRepository<Usehealth, Long> {
    
    fun findByOrder(order: Long): List<Usehealth>
    
    fun findByHealth(health: Long): List<Usehealth>
    
    fun findByUser(user: Long): List<Usehealth>
    
    fun findByRocker(rocker: Long): List<Usehealth>
    
    fun findByTerm(term: Long): List<Usehealth>
    
    fun findByDiscount(discount: Long): List<Usehealth>
    
    override fun findAll(pageable: Pageable): Page<Usehealth>
}