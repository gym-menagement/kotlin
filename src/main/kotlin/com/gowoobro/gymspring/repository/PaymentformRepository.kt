package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Paymentform
import com.gowoobro.gymspring.entity.PaymentformCreateRequest
import com.gowoobro.gymspring.entity.PaymentformUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PaymentformRepository : JpaRepository<Paymentform, Long> {
    override fun findAll(pageable: Pageable): Page<Paymentform>

    override fun findById(id: String): List<Paymentform>

    override fun findByGym(gym: String): List<Paymentform>

    override fun findByPayment(payment: String): List<Paymentform>

    override fun findByType(type: String): List<Paymentform>

    override fun findByCost(cost: String): List<Paymentform>

    override fun findByDate(date: String): List<Paymentform>
}