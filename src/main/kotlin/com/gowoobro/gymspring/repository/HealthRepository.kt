package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Health
import com.gowoobro.gymspring.entity.HealthCreateRequest
import com.gowoobro.gymspring.entity.HealthUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface HealthRepository : JpaRepository<Health, Long> {
    override fun findAll(pageable: Pageable): Page<Health>


    fun findByCategory(category: Long): List<Health>

    fun findByTerm(term: Long): List<Health>

    fun findByName(name: String): List<Health>

    fun findByCount(count: Int): List<Health>

    fun findByCost(cost: Int): List<Health>

    fun findByDiscount(discount: Long): List<Health>

    fun findByCostdiscount(costdiscount: Int): List<Health>

    fun findByContent(content: String): List<Health>

    fun findByDate(date: LocalDateTime): List<Health>
}