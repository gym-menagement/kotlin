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
    @Query("SELECT m FROM Discount m")
    override fun findAll(pageable: Pageable): Page<Discount>

    @Query("SELECT m FROM Discount m WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Discount>

    @Query("SELECT m FROM Discount m WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Discount>

    @Query("SELECT m FROM Discount m WHERE m.discount = :discount")
    fun findByDiscountWithJoin(discount: Int): List<Discount>

    @Query("SELECT m FROM Discount m WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Discount>
}