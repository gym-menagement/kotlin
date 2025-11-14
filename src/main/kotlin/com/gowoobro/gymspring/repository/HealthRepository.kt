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
    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount")
    override fun findAll(pageable: Pageable): Page<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.categoryId = :healthcategory")
    fun findByCategoryWithJoin(healthcategory: Long): List<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.termId = :term")
    fun findByTermWithJoin(term: Long): List<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.count = :count")
    fun findByCountWithJoin(count: Int): List<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.cost = :cost")
    fun findByCostWithJoin(cost: Int): List<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.discountId = :discount")
    fun findByDiscountWithJoin(discount: Long): List<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.costdiscount = :costdiscount")
    fun findByCostdiscountWithJoin(costdiscount: Int): List<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.content = :content")
    fun findByContentWithJoin(content: String): List<Health>

    @Query("SELECT m FROM Health m LEFT JOIN FETCH m.healthcategory LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Health>
}