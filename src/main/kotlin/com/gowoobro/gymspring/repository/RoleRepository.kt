package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Role
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    
    fun findByGym(gym: Long): List<Role>
    
    fun findByRole(role: Int): List<Role>
    
    fun findByNameContaining(name: String): List<Role>
    
    override fun findAll(pageable: Pageable): Page<Role>
}