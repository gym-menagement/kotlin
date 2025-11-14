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
    @Query("SELECT m FROM Alarm m LEFT JOIN FETCH m.user")
    override fun findAll(pageable: Pageable): Page<Alarm>

    @Query("SELECT m FROM Alarm m LEFT JOIN FETCH m.user WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Alarm>

    @Query("SELECT m FROM Alarm m LEFT JOIN FETCH m.user WHERE m.title = :title")
    fun findByTitleWithJoin(title: String): List<Alarm>

    @Query("SELECT m FROM Alarm m LEFT JOIN FETCH m.user WHERE m.content = :content")
    fun findByContentWithJoin(content: String): List<Alarm>

    @Query("SELECT m FROM Alarm m LEFT JOIN FETCH m.user WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<Alarm>

    @Query("SELECT m FROM Alarm m LEFT JOIN FETCH m.user WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Alarm>

    @Query("SELECT m FROM Alarm m LEFT JOIN FETCH m.user WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Alarm>

    @Query("SELECT m FROM Alarm m LEFT JOIN FETCH m.user WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Alarm>
}