package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Systemlog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface SystemlogRepository : JpaRepository<Systemlog, Long> {
    
    fun findByType(type: Type): List<Systemlog>
    
    fun findByContentContaining(content: String): List<Systemlog>
    
    fun findByResult(result: Int): List<Systemlog>
    
    override fun findAll(pageable: Pageable): Page<Systemlog>
}