package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Payment
import com.gowoobro.gymspring.entity.PaymentCreateRequest
import com.gowoobro.gymspring.entity.PaymentUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime




@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {
    @EntityGraph(attributePaths = [
        "gym",
        "order",
        "order.membership",
        "membership",
        "membership.gym",
        "membership.user"
    ])
    override fun findAll(pageable: Pageable): Page<Payment>

    @EntityGraph(attributePaths = [
        "gym",
        "order",
        "order.membership",
        "membership",
        "membership.gym",
        "membership.user"
    ])
    override fun findById(id: Long): java.util.Optional<Payment>

    @EntityGraph(attributePaths = [
        "gym",
        "order",
        "order.membership",
        "membership",
        "membership.gym",
        "membership.user"
    ])
    fun findBygymId(gym: Long): List<Payment>

    @EntityGraph(attributePaths = [
        "gym",
        "order",
        "order.membership",
        "membership",
        "membership.gym",
        "membership.user"
    ])
    fun findByorderId(order: Long): List<Payment>

    @EntityGraph(attributePaths = [
        "gym",
        "order",
        "order.membership",
        "membership",
        "membership.gym",
        "membership.user"
    ])
    fun findBymembershipId(membership: Long): List<Payment>

    @EntityGraph(attributePaths = [
        "gym",
        "order",
        "order.membership",
        "membership",
        "membership.gym",
        "membership.user"
    ])
    fun findByCost(cost: Int): List<Payment>

    @EntityGraph(attributePaths = [
        "gym",
        "order",
        "order.membership",
        "membership",
        "membership.gym",
        "membership.user"
    ])
    fun findByDate(date: LocalDateTime): List<Payment>
}
