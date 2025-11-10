package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Discount
import com.gowoobro.gymspring.entity.DiscountCreateRequest
import com.gowoobro.gymspring.entity.DiscountUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface DiscountRepository : JpaRepository<Discount, Long> {
    override fun findAll(pageable: Pageable): Page<Discount>


    fun findByName(name: String): List<Discount>

    fun findByDiscount(discount: Int): List<Discount>

    fun findByDate(date: LocalDateTime): List<Discount>
}