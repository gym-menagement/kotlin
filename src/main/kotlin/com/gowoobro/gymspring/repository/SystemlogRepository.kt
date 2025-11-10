package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Systemlog
import com.gowoobro.gymspring.entity.SystemlogCreateRequest
import com.gowoobro.gymspring.entity.SystemlogUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.systemlog.Type


@Repository
interface SystemlogRepository : JpaRepository<Systemlog, Long> {
    override fun findAll(pageable: Pageable): Page<Systemlog>


    fun findByType(type: Type): List<Systemlog>

    fun findByContent(content: String): List<Systemlog>

    fun findByResult(result: Int): List<Systemlog>

    fun findByDate(date: LocalDateTime): List<Systemlog>
}