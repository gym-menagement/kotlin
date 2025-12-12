package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Healthcategory
import com.gowoobro.gymspring.entity.HealthcategoryCreateRequest
import com.gowoobro.gymspring.entity.HealthcategoryUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime




@Repository
interface HealthcategoryRepository : JpaRepository<Healthcategory, Long> {
    @EntityGraph(attributePaths = [
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Healthcategory>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Healthcategory>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByGym(gym: Long): List<Healthcategory>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByName(name: String): List<Healthcategory>

    @EntityGraph(attributePaths = [
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Healthcategory>
}
