package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Payment
import com.gowoobro.gymspring.entity.PaymentCreateRequest
import com.gowoobro.gymspring.entity.PaymentUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {
    override fun findAll(pageable: Pageable): Page<Payment>

    override fun findById(id: String): List<Payment>

    override fun findByGym(gym: String): List<Payment>

    override fun findByOrder(order: String): List<Payment>

    override fun findByMembership(membership: String): List<Payment>

    override fun findByCost(cost: String): List<Payment>

    override fun findByDate(date: String): List<Payment>
}