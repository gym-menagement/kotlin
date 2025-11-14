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
    @Query("SELECT m FROM Paymentform m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.payment LEFT JOIN FETCH m.paymenttype")
    override fun findAll(pageable: Pageable): Page<Paymentform>

    @Query("SELECT m FROM Paymentform m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.payment LEFT JOIN FETCH m.paymenttype WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Paymentform>

    @Query("SELECT m FROM Paymentform m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.payment LEFT JOIN FETCH m.paymenttype WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Paymentform>

    @Query("SELECT m FROM Paymentform m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.payment LEFT JOIN FETCH m.paymenttype WHERE m.paymentId = :payment")
    fun findByPaymentWithJoin(payment: Long): List<Paymentform>

    @Query("SELECT m FROM Paymentform m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.payment LEFT JOIN FETCH m.paymenttype WHERE m.typeId = :paymenttype")
    fun findByTypeWithJoin(paymenttype: Long): List<Paymentform>

    @Query("SELECT m FROM Paymentform m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.payment LEFT JOIN FETCH m.paymenttype WHERE m.cost = :cost")
    fun findByCostWithJoin(cost: Int): List<Paymentform>

    @Query("SELECT m FROM Paymentform m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.payment LEFT JOIN FETCH m.paymenttype WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Paymentform>
}