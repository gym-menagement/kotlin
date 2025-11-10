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



@Repository
interface PushtokenRepository : JpaRepository<Pushtoken, Long> {
    override fun findAll(pageable: Pageable): Page<Pushtoken>


    fun findByUser(user: Long): List<Pushtoken>

    fun findByToken(token: String): List<Pushtoken>

    fun findByDevicetype(devicetype: String): List<Pushtoken>

    fun findByDeviceid(deviceid: String): List<Pushtoken>

    fun findByAppversion(appversion: String): List<Pushtoken>

    fun findByIsactive(isactive: Int): List<Pushtoken>

    fun findByCreateddate(createddate: LocalDateTime): List<Pushtoken>

    fun findByUpdateddate(updateddate: LocalDateTime): List<Pushtoken>
}