package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Healthcategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface HealthcategoryRepository : JpaRepository<Healthcategory, Long> {
    
    fun findByGym(gym: Long): List<Healthcategory>
    
    fun findByNameContaining(name: String): List<Healthcategory>
    
    override fun findAll(pageable: Pageable): Page<Healthcategory>
}