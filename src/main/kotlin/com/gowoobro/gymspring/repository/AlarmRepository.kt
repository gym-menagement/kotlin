package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Alarm
import com.gowoobro.gymspring.entity.AlarmCreateRequest
import com.gowoobro.gymspring.entity.AlarmUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.alarm.Type
import com.gowoobro.gymspring.enums.alarm.Status


@Repository
interface AlarmRepository : JpaRepository<Alarm, Long> {
    @EntityGraph(attributePaths = ["user"])
    override fun findAll(pageable: Pageable): Page<Alarm>

    @EntityGraph(attributePaths = ["user"])
    override fun findById(id: Long): java.util.Optional<Alarm>

    @EntityGraph(attributePaths = ["user"])
    fun findByTitle(title: String): List<Alarm>

    @EntityGraph(attributePaths = ["user"])
    fun findByContent(content: String): List<Alarm>

    @EntityGraph(attributePaths = ["user"])
    fun findByType(type: Type): List<Alarm>

    @EntityGraph(attributePaths = ["user"])
    fun findByStatus(status: Status): List<Alarm>

    @EntityGraph(attributePaths = ["user"])
    fun findByuserId(user: Long): List<Alarm>

    @EntityGraph(attributePaths = ["user"])
    fun findByDate(date: LocalDateTime): List<Alarm>
}
