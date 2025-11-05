package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Daytype
import com.gowoobro.gymspring.entity.DaytypeCreateRequest
import com.gowoobro.gymspring.entity.DaytypeUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DaytypeRepository : JpaRepository<Daytype, Long> {
    override fun findAll(pageable: Pageable): Page<Daytype>

    override fun findById(id: String): List<Daytype>

    override fun findByGym(gym: String): List<Daytype>

    override fun findByName(name: String): List<Daytype>

    override fun findByDate(date: String): List<Daytype>
}