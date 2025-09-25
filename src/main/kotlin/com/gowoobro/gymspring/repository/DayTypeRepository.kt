package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Daytype
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface DaytypeRepository : JpaRepository<Daytype, Long> {
    
    fun findByGym(gym: Long): List<Daytype>
    
    fun findByNameContaining(name: String): List<Daytype>
    
    override fun findAll(pageable: Pageable): Page<Daytype>
}