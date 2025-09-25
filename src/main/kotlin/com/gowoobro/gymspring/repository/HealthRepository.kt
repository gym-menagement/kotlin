package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Health
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface HealthRepository : JpaRepository<Health, Long> {
    
    fun findByCategory(category: Long): List<Health>
    
    fun findByTerm(term: Long): List<Health>
    
    fun findByNameContaining(name: String): List<Health>
    
    fun findByCount(count: Int): List<Health>
    
    fun findByCost(cost: Int): List<Health>
    
    fun findByDiscount(discount: Long): List<Health>
    
    fun findByCostdiscount(costdiscount: Int): List<Health>
    
    fun findByContentContaining(content: String): List<Health>
    
    override fun findAll(pageable: Pageable): Page<Health>
}