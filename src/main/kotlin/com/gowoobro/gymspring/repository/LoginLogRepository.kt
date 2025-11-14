package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Loginlog
import com.gowoobro.gymspring.entity.LoginlogCreateRequest
import com.gowoobro.gymspring.entity.LoginlogUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface LoginlogRepository : JpaRepository<Loginlog, Long> {
    @Query("SELECT m FROM Loginlog m LEFT JOIN FETCH m.user")
    override fun findAll(pageable: Pageable): Page<Loginlog>

    @Query("SELECT m FROM Loginlog m LEFT JOIN FETCH m.user WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Loginlog>

    @Query("SELECT m FROM Loginlog m LEFT JOIN FETCH m.user WHERE m.ip = :ip")
    fun findByIpWithJoin(ip: String): List<Loginlog>

    @Query("SELECT m FROM Loginlog m LEFT JOIN FETCH m.user WHERE m.ipvalue = :ipvalue")
    fun findByIpvalueWithJoin(ipvalue: Long): List<Loginlog>

    @Query("SELECT m FROM Loginlog m LEFT JOIN FETCH m.user WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Loginlog>

    @Query("SELECT m FROM Loginlog m LEFT JOIN FETCH m.user WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Loginlog>
}