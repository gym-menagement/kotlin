package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Ipblock
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status
import com.gowoobro.gymspring.entity.Policy
import com.gowoobro.gymspring.entity.Use

@Repository
interface IpblockRepository : JpaRepository<Ipblock, Long> {
    
    fun findByAddressContaining(address: String): List<Ipblock>
    
    fun findByType(type: Type): List<Ipblock>
    
    fun findByPolicy(policy: Policy): List<Ipblock>
    
    fun findByUse(use: Use): List<Ipblock>
    
    fun findByOrder(order: Int): List<Ipblock>
    
    override fun findAll(pageable: Pageable): Page<Ipblock>
}