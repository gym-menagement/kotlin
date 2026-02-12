package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Gymadmin
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GymadminRepository : JpaRepository<Gymadmin, Long> {
    fun findByGymId(gymId: Long, pageable: Pageable): Page<Gymadmin>
    fun findByUserId(userId: Long, pageable: Pageable): Page<Gymadmin>
    
    // Check if user is admin of a specific gym
    fun existsByGymIdAndUserId(gymId: Long, userId: Long): Boolean
    
    // Find specific mapping
    fun findByGymIdAndUserId(gymId: Long, userId: Long): Gymadmin?
}
