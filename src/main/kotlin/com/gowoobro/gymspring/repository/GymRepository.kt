package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Gym
import com.gowoobro.gymspring.entity.GymCreateRequest
import com.gowoobro.gymspring.entity.GymUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface GymRepository : JpaRepository<Gym, Long> {
    @Query("SELECT m FROM Gym m")
    override fun findAll(pageable: Pageable): Page<Gym>

    @Query("SELECT m FROM Gym m WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Gym>

    @Query("SELECT m FROM Gym m WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Gym>

    @Query("SELECT m FROM Gym m WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Gym>
}