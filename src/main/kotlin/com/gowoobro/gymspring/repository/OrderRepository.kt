package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Order
import com.gowoobro.gymspring.entity.OrderCreateRequest
import com.gowoobro.gymspring.entity.OrderUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    override fun findAll(pageable: Pageable): Page<Order>

    override fun findById(id: String): List<Order>

    override fun findByMembership(membership: String): List<Order>

    override fun findByDate(date: String): List<Order>
}