package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Paymenttype
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface PaymenttypeRepository : JpaRepository<Paymenttype, Long> {
    
    fun findByGym(gym: Long): List<Paymenttype>
    
    fun findByNameContaining(name: String): List<Paymenttype>
    
    override fun findAll(pageable: Pageable): Page<Paymenttype>
}