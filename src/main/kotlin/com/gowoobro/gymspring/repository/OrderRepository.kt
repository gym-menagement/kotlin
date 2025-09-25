package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    
    fun findByMembership(membership: Long): List<Order>
    
    override fun findAll(pageable: Pageable): Page<Order>
}