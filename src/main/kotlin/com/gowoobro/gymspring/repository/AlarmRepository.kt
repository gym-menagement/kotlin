package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Alarm
import com.gowoobro.gymspring.entity.AlarmCreateRequest
import com.gowoobro.gymspring.entity.AlarmUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AlarmRepository : JpaRepository<Alarm, Long> {
    override fun findAll(pageable: Pageable): Page<Alarm>

    override fun findById(id: String): List<Alarm>

    override fun findByTitle(title: String): List<Alarm>

    override fun findByContent(content: String): List<Alarm>

    override fun findByType(type: String): List<Alarm>

    override fun findByStatus(status: String): List<Alarm>

    override fun findByUser(user: String): List<Alarm>

    override fun findByDate(date: String): List<Alarm>
}