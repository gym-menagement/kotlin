package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Discount
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface DiscountRepository : JpaRepository<Discount, Long> {
    
    fun findByNameContaining(name: String): List<Discount>
    
    fun findByDiscount(discount: Int): List<Discount>
    
    override fun findAll(pageable: Pageable): Page<Discount>
}