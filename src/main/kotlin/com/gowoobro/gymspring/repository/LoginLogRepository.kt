package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Loginlog
import com.gowoobro.gymspring.entity.LoginlogCreateRequest
import com.gowoobro.gymspring.entity.LoginlogUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime




@Repository
interface LoginlogRepository : JpaRepository<Loginlog, Long> {
    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<Loginlog>

    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<Loginlog>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByIp(ip: String): List<Loginlog>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByIpvalue(ipvalue: Long): List<Loginlog>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByUser(user: Long): List<Loginlog>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByDate(date: LocalDateTime): List<Loginlog>
}
