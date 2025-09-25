package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Setting
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface SettingRepository : JpaRepository<Setting, Long> {
    
    fun findByCategoryContaining(category: String): List<Setting>
    
    fun findByNameContaining(name: String): List<Setting>
    
    fun findByKeyContaining(key: String): List<Setting>
    
    fun findByValueContaining(value: String): List<Setting>
    
    fun findByRemarkContaining(remark: String): List<Setting>
    
    fun findByType(type: Type): List<Setting>
    
    fun findByDataContaining(data: String): List<Setting>
    
    fun findByOrder(order: Int): List<Setting>
    
    override fun findAll(pageable: Pageable): Page<Setting>
}