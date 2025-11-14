package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Pushtoken
import com.gowoobro.gymspring.entity.PushtokenCreateRequest
import com.gowoobro.gymspring.entity.PushtokenUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.pushtoken.Isactive


@Repository
interface PushtokenRepository : JpaRepository<Pushtoken, Long> {
    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user")
    override fun findAll(pageable: Pageable): Page<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.token = :token")
    fun findByTokenWithJoin(token: String): List<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.devicetype = :devicetype")
    fun findByDevicetypeWithJoin(devicetype: String): List<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.deviceid = :deviceid")
    fun findByDeviceidWithJoin(deviceid: String): List<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.appversion = :appversion")
    fun findByAppversionWithJoin(appversion: String): List<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.isactive = :isactive")
    fun findByIsactiveWithJoin(isactive: Isactive): List<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.createddate = :createddate")
    fun findByCreateddateWithJoin(createddate: LocalDateTime): List<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.updateddate = :updateddate")
    fun findByUpdateddateWithJoin(updateddate: LocalDateTime): List<Pushtoken>

    @Query("SELECT m FROM Pushtoken m LEFT JOIN FETCH m.user WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Pushtoken>
}