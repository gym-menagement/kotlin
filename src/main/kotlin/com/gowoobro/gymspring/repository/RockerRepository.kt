package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rocker
import com.gowoobro.gymspring.entity.RockerCreateRequest
import com.gowoobro.gymspring.entity.RockerUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.rocker.Available


@Repository
interface RockerRepository : JpaRepository<Rocker, Long> {
    @EntityGraph(attributePaths = ["rockergroup"])
    override fun findAll(pageable: Pageable): Page<Rocker>

    @EntityGraph(attributePaths = ["rockergroup"])
    override fun findById(id: Long): java.util.Optional<Rocker>

    @EntityGraph(attributePaths = ["rockergroup"])
    fun findBygroupId(rockergroup: Long): List<Rocker>

    @EntityGraph(attributePaths = ["rockergroup"])
    fun findByName(name: String): List<Rocker>

    @EntityGraph(attributePaths = ["rockergroup"])
    fun findByAvailable(available: Available): List<Rocker>

    @EntityGraph(attributePaths = ["rockergroup"])
    fun findByDate(date: LocalDateTime): List<Rocker>
}
