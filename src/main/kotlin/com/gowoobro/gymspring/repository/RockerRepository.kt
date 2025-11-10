package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rocker
import com.gowoobro.gymspring.entity.RockerCreateRequest
import com.gowoobro.gymspring.entity.RockerUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface RockerRepository : JpaRepository<Rocker, Long> {
    override fun findAll(pageable: Pageable): Page<Rocker>


    fun findByGroup(group: Long): List<Rocker>

    fun findByName(name: String): List<Rocker>

    fun findByAvailable(available: Int): List<Rocker>

    fun findByDate(date: LocalDateTime): List<Rocker>
}