package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Payment
import com.gowoobro.gymspring.entity.PaymentCreateRequest
import com.gowoobro.gymspring.entity.PaymentUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {
    override fun findAll(pageable: Pageable): Page<Payment>


    fun findByGym(gym: Long): List<Payment>

    fun findByOrder(order: Long): List<Payment>

    fun findByMembership(membership: Long): List<Payment>

    fun findByCost(cost: Int): List<Payment>

    fun findByDate(date: LocalDateTime): List<Payment>
}