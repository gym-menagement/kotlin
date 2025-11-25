package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Health
import com.gowoobro.gymspring.entity.HealthCreateRequest
import com.gowoobro.gymspring.entity.HealthUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime




@Repository
interface HealthRepository : JpaRepository<Health, Long> {
    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findBycategoryId(healthcategory: Long): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findBytermId(term: Long): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByName(name: String): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByCount(count: Int): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByCost(cost: Int): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findBydiscountId(discount: Long): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByCostdiscount(costdiscount: Int): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByContent(content: String): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findBygymId(gym: Long): List<Health>

    @EntityGraph(attributePaths = [
        "healthcategory",
        "healthcategory.gym",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Health>
}
