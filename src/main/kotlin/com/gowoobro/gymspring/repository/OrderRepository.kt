package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Order
import com.gowoobro.gymspring.entity.OrderCreateRequest
import com.gowoobro.gymspring.entity.OrderUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime




@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = [
        "user",
        "gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    override fun findAll(pageable: Pageable): Page<Order>

    @EntityGraph(attributePaths = [
        "user",
        "gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    override fun findById(id: Long): java.util.Optional<Order>

    @EntityGraph(attributePaths = [
        "user",
        "gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByuserId(user: Long): List<Order>

    @EntityGraph(attributePaths = [
        "user",
        "gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findBygymId(gym: Long): List<Order>

    @EntityGraph(attributePaths = [
        "user",
        "gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByhealthId(health: Long): List<Order>

    @EntityGraph(attributePaths = [
        "user",
        "gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByDate(date: LocalDateTime): List<Order>
}
