package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Health
import com.gowoobro.gymspring.entity.HealthCreateRequest
import com.gowoobro.gymspring.entity.HealthUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface HealthRepository : JpaRepository<Health, Long> {
    override fun findAll(pageable: Pageable): Page<Health>

    override fun findById(id: String): List<Health>

    override fun findByCategory(category: String): List<Health>

    override fun findByTerm(term: String): List<Health>

    override fun findByName(name: String): List<Health>

    override fun findByCount(count: String): List<Health>

    override fun findByCost(cost: String): List<Health>

    override fun findByDiscount(discount: String): List<Health>

    override fun findByCostdiscount(costdiscount: String): List<Health>

    override fun findByContent(content: String): List<Health>

    override fun findByDate(date: String): List<Health>
}