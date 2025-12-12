package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Pushtoken
import com.gowoobro.gymspring.entity.PushtokenCreateRequest
import com.gowoobro.gymspring.entity.PushtokenUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.pushtoken.Isactive



@Repository
interface PushtokenRepository : JpaRepository<Pushtoken, Long> {
    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByUser(user: Long): List<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByToken(token: String): List<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByDevicetype(devicetype: String): List<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByDeviceid(deviceid: String): List<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByAppversion(appversion: String): List<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByIsactive(isactive: Isactive): List<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByCreateddate(createddate: LocalDateTime): List<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByUpdateddate(updateddate: LocalDateTime): List<Pushtoken>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByDate(date: LocalDateTime): List<Pushtoken>
}
