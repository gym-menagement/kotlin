package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Healthcategory
import com.gowoobro.gymspring.entity.HealthcategoryCreateRequest
import com.gowoobro.gymspring.entity.HealthcategoryUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface HealthcategoryRepository : JpaRepository<Healthcategory, Long> {
    @Query("SELECT m FROM Healthcategory m LEFT JOIN FETCH m.gym")
    override fun findAll(pageable: Pageable): Page<Healthcategory>

    @Query("SELECT m FROM Healthcategory m LEFT JOIN FETCH m.gym WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Healthcategory>

    @Query("SELECT m FROM Healthcategory m LEFT JOIN FETCH m.gym WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Healthcategory>

    @Query("SELECT m FROM Healthcategory m LEFT JOIN FETCH m.gym WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Healthcategory>

    @Query("SELECT m FROM Healthcategory m LEFT JOIN FETCH m.gym WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Healthcategory>
}