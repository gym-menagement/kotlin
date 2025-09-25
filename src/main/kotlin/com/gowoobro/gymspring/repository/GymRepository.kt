package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Gym
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface GymRepository : JpaRepository<Gym, Long> {
    
    fun findByNameContaining(name: String): List<Gym>
    
    override fun findAll(pageable: Pageable): Page<Gym>
}