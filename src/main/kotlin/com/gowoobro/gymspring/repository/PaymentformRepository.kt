package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Paymentform
import com.gowoobro.gymspring.entity.PaymentformCreateRequest
import com.gowoobro.gymspring.entity.PaymentformUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface PaymentformRepository : JpaRepository<Paymentform, Long> {
    @EntityGraph(attributePaths = ["gym", "payment", "paymenttype"])
    override fun findAll(pageable: Pageable): Page<Paymentform>

    @EntityGraph(attributePaths = ["gym", "payment", "paymenttype"])
    override fun findById(id: Long): java.util.Optional<Paymentform>

    @EntityGraph(attributePaths = ["gym", "payment", "paymenttype"])
    fun findBygymId(gym: Long): List<Paymentform>

    @EntityGraph(attributePaths = ["gym", "payment", "paymenttype"])
    fun findBypaymentId(payment: Long): List<Paymentform>

    @EntityGraph(attributePaths = ["gym", "payment", "paymenttype"])
    fun findBytypeId(paymenttype: Long): List<Paymentform>

    @EntityGraph(attributePaths = ["gym", "payment", "paymenttype"])
    fun findByCost(cost: Int): List<Paymentform>

    @EntityGraph(attributePaths = ["gym", "payment", "paymenttype"])
    fun findByDate(date: LocalDateTime): List<Paymentform>
}
