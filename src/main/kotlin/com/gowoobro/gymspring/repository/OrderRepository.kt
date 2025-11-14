package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Order
import com.gowoobro.gymspring.entity.OrderCreateRequest
import com.gowoobro.gymspring.entity.OrderUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    @Query("SELECT m FROM Order m LEFT JOIN FETCH m.membership")
    override fun findAll(pageable: Pageable): Page<Order>

    @Query("SELECT m FROM Order m LEFT JOIN FETCH m.membership WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Order>

    @Query("SELECT m FROM Order m LEFT JOIN FETCH m.membership WHERE m.membershipId = :membership")
    fun findByMembershipWithJoin(membership: Long): List<Order>

    @Query("SELECT m FROM Order m LEFT JOIN FETCH m.membership WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Order>
}