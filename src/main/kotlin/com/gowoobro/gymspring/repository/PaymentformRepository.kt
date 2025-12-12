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
    @EntityGraph(attributePaths = [
        "gym",
        "payment",
        "payment.gym",
        "payment.order",
        "payment.user",
        "paymenttype",
        "paymenttype.gym"
    ])
    override fun findAll(pageable: Pageable): Page<Paymentform>

    @EntityGraph(attributePaths = [
        "gym",
        "payment",
        "payment.gym",
        "payment.order",
        "payment.user",
        "paymenttype",
        "paymenttype.gym"
    ])
    override fun findById(id: Long): java.util.Optional<Paymentform>

    @EntityGraph(attributePaths = [
        "gym",
        "payment",
        "payment.gym",
        "payment.order",
        "payment.user",
        "paymenttype",
        "paymenttype.gym"
    ])
    fun findByGym(gym: Long): List<Paymentform>

    @EntityGraph(attributePaths = [
        "gym",
        "payment",
        "payment.gym",
        "payment.order",
        "payment.user",
        "paymenttype",
        "paymenttype.gym"
    ])
    fun findByPayment(payment: Long): List<Paymentform>

    @EntityGraph(attributePaths = [
        "gym",
        "payment",
        "payment.gym",
        "payment.order",
        "payment.user",
        "paymenttype",
        "paymenttype.gym"
    ])
    fun findByType(type: Long): List<Paymentform>

    @EntityGraph(attributePaths = [
        "gym",
        "payment",
        "payment.gym",
        "payment.order",
        "payment.user",
        "paymenttype",
        "paymenttype.gym"
    ])
    fun findByCost(cost: Int): List<Paymentform>

    @EntityGraph(attributePaths = [
        "gym",
        "payment",
        "payment.gym",
        "payment.order",
        "payment.user",
        "paymenttype",
        "paymenttype.gym"
    ])
    fun findByDate(date: LocalDateTime): List<Paymentform>
}
