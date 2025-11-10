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
    override fun findAll(pageable: Pageable): Page<Loginlog>

    fun findByIp(ip: String): List<Loginlog>

    fun findByIpvalue(ipvalue: Long): List<Loginlog>

    fun findByUser(user: Long): List<Loginlog>

    fun findByDate(date: LocalDateTime): List<Loginlog>
}