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
    @Query("SELECT m FROM Payment m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.order LEFT JOIN FETCH m.membership")
    override fun findAll(pageable: Pageable): Page<Payment>

    @Query("SELECT m FROM Payment m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.order LEFT JOIN FETCH m.membership WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Payment>

    @Query("SELECT m FROM Payment m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.order LEFT JOIN FETCH m.membership WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Payment>

    @Query("SELECT m FROM Payment m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.order LEFT JOIN FETCH m.membership WHERE m.orderId = :order")
    fun findByOrderWithJoin(order: Long): List<Payment>

    @Query("SELECT m FROM Payment m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.order LEFT JOIN FETCH m.membership WHERE m.membershipId = :membership")
    fun findByMembershipWithJoin(membership: Long): List<Payment>

    @Query("SELECT m FROM Payment m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.order LEFT JOIN FETCH m.membership WHERE m.cost = :cost")
    fun findByCostWithJoin(cost: Int): List<Payment>

    @Query("SELECT m FROM Payment m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.order LEFT JOIN FETCH m.membership WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Payment>
}