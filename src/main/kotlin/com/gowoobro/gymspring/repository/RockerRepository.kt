package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rocker
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface RockerRepository : JpaRepository<Rocker, Long> {
    
    fun findByGroup(group: Long): List<Rocker>
    
    fun findByNameContaining(name: String): List<Rocker>
    
    fun findByAvailable(available: Int): List<Rocker>
    
    override fun findAll(pageable: Pageable): Page<Rocker>
}