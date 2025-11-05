package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Gym
import com.gowoobro.gymspring.entity.GymCreateRequest
import com.gowoobro.gymspring.entity.GymUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GymRepository : JpaRepository<Gym, Long> {
    override fun findAll(pageable: Pageable): Page<Gym>

    override fun findById(id: String): List<Gym>

    override fun findByName(name: String): List<Gym>

    override fun findByDate(date: String): List<Gym>
}