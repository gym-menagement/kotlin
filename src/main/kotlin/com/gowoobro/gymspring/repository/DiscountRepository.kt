package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Discount
import com.gowoobro.gymspring.entity.DiscountCreateRequest
import com.gowoobro.gymspring.entity.DiscountUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime




@Repository
interface DiscountRepository : JpaRepository<Discount, Long> {
    @EntityGraph(attributePaths = [
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Discount>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Discount>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByGym(gym: Long): List<Discount>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByName(name: String): List<Discount>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByDiscount(discount: Int): List<Discount>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Discount>
}
