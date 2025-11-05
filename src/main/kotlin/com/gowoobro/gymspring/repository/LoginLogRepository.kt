package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Loginlog
import com.gowoobro.gymspring.entity.LoginlogCreateRequest
import com.gowoobro.gymspring.entity.LoginlogUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LoginlogRepository : JpaRepository<Loginlog, Long> {
    override fun findAll(pageable: Pageable): Page<Loginlog>

    override fun findById(id: String): List<Loginlog>

    override fun findByIp(ip: String): List<Loginlog>

    override fun findByIpvalue(ipvalue: String): List<Loginlog>

    override fun findByUser(user: String): List<Loginlog>

    override fun findByDate(date: String): List<Loginlog>
}