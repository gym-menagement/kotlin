package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Daytype
import com.gowoobro.gymspring.entity.DaytypeCreateRequest
import com.gowoobro.gymspring.entity.DaytypeUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface DaytypeRepository : JpaRepository<Daytype, Long> {
    override fun findAll(pageable: Pageable): Page<Daytype>

    fun findByGym(gym: Long): List<Daytype>

    fun findByName(name: String): List<Daytype>

    fun findByDate(date: LocalDateTime): List<Daytype>
}