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
import com.gowoobro.gymspring.enums.systemlog.Result


@Repository
interface SystemlogRepository : JpaRepository<Systemlog, Long> {
    @Query("SELECT m FROM Systemlog m")
    override fun findAll(pageable: Pageable): Page<Systemlog>

    @Query("SELECT m FROM Systemlog m WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Systemlog>

    @Query("SELECT m FROM Systemlog m WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<Systemlog>

    @Query("SELECT m FROM Systemlog m WHERE m.content = :content")
    fun findByContentWithJoin(content: String): List<Systemlog>

    @Query("SELECT m FROM Systemlog m WHERE m.result = :result")
    fun findByResultWithJoin(result: Result): List<Systemlog>

    @Query("SELECT m FROM Systemlog m WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Systemlog>
}