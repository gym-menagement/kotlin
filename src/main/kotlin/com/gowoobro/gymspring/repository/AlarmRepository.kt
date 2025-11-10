package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Alarm
import com.gowoobro.gymspring.entity.AlarmCreateRequest
import com.gowoobro.gymspring.entity.AlarmUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.alarm.Type
import com.gowoobro.gymspring.enums.alarm.Status


@Repository
interface AlarmRepository : JpaRepository<Alarm, Long> {
    override fun findAll(pageable: Pageable): Page<Alarm>

    fun findByTitle(title: String): List<Alarm>

    fun findByContent(content: String): List<Alarm>

    fun findByType(type: Type): List<Alarm>

    fun findByStatus(status: Status): List<Alarm>

    fun findByUser(user: Long): List<Alarm>

    fun findByDate(date: LocalDateTime): List<Alarm>
}