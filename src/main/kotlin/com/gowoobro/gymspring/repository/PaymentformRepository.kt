package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Paymentform
import com.gowoobro.gymspring.entity.PaymentformCreateRequest
import com.gowoobro.gymspring.entity.PaymentformUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface PaymentformRepository : JpaRepository<Paymentform, Long> {
    override fun findAll(pageable: Pageable): Page<Paymentform>

    fun findByGym(gym: Long): List<Paymentform>

    fun findByPayment(payment: Long): List<Paymentform>

    fun findByType(type: Long): List<Paymentform>

    fun findByCost(cost: Int): List<Paymentform>

    fun findByDate(date: LocalDateTime): List<Paymentform>
}