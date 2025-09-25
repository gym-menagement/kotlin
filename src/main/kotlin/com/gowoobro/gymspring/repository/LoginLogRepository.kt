package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Loginlog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface LoginlogRepository : JpaRepository<Loginlog, Long> {
    
    fun findByIpContaining(ip: String): List<Loginlog>
    
    fun findByIpvalue(ipvalue: Long): List<Loginlog>
    
    fun findByUser(user: Long): List<Loginlog>
    
    override fun findAll(pageable: Pageable): Page<Loginlog>
}