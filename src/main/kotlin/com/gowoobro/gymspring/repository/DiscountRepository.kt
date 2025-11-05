package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Discount
import com.gowoobro.gymspring.entity.DiscountCreateRequest
import com.gowoobro.gymspring.entity.DiscountUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DiscountRepository : JpaRepository<Discount, Long> {
    override fun findAll(pageable: Pageable): Page<Discount>

    override fun findById(id: String): List<Discount>

    override fun findByName(name: String): List<Discount>

    override fun findByDiscount(discount: String): List<Discount>

    override fun findByDate(date: String): List<Discount>
}