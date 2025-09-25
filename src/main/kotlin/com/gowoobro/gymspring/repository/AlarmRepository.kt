package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Alarm
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface AlarmRepository : JpaRepository<Alarm, Long> {
    
    fun findByTitleContaining(title: String): List<Alarm>
    
    fun findByContentContaining(content: String): List<Alarm>
    
    fun findByType(type: Type): List<Alarm>
    
    fun findByStatus(status: Status): List<Alarm>
    
    fun findByUser(user: Long): List<Alarm>
    
    override fun findAll(pageable: Pageable): Page<Alarm>
}