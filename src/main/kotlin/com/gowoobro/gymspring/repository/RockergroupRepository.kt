package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rockergroup
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface RockergroupRepository : JpaRepository<Rockergroup, Long> {
    
    fun findByGym(gym: Long): List<Rockergroup>
    
    fun findByNameContaining(name: String): List<Rockergroup>
    
    override fun findAll(pageable: Pageable): Page<Rockergroup>
}