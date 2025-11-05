package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rocker
import com.gowoobro.gymspring.entity.RockerCreateRequest
import com.gowoobro.gymspring.entity.RockerUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RockerRepository : JpaRepository<Rocker, Long> {
    override fun findAll(pageable: Pageable): Page<Rocker>

    override fun findById(id: String): List<Rocker>

    override fun findByGroup(group: String): List<Rocker>

    override fun findByName(name: String): List<Rocker>

    override fun findByAvailable(available: String): List<Rocker>

    override fun findByDate(date: String): List<Rocker>
}