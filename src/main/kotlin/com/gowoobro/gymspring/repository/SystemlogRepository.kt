package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Systemlog
import com.gowoobro.gymspring.entity.SystemlogCreateRequest
import com.gowoobro.gymspring.entity.SystemlogUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SystemlogRepository : JpaRepository<Systemlog, Long> {
    override fun findAll(pageable: Pageable): Page<Systemlog>

    override fun findById(id: String): List<Systemlog>

    override fun findByType(type: String): List<Systemlog>

    override fun findByContent(content: String): List<Systemlog>

    override fun findByResult(result: String): List<Systemlog>

    override fun findByDate(date: String): List<Systemlog>
}