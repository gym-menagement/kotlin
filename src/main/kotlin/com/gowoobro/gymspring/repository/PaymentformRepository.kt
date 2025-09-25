package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Paymentform
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface PaymentformRepository : JpaRepository<Paymentform, Long> {
    
    fun findByGym(gym: Long): List<Paymentform>
    
    fun findByPayment(payment: Long): List<Paymentform>
    
    fun findByType(type: Long): List<Paymentform>
    
    fun findByCost(cost: Int): List<Paymentform>
    
    override fun findAll(pageable: Pageable): Page<Paymentform>
}