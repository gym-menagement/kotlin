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
    override fun findAll(pageable: Pageable): Page<Gym>

    fun findByName(name: String): List<Gym>

    fun findByDate(date: LocalDateTime): List<Gym>
}