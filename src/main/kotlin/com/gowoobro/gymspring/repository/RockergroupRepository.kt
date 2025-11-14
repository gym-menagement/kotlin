package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rockergroup
import com.gowoobro.gymspring.entity.RockergroupCreateRequest
import com.gowoobro.gymspring.entity.RockergroupUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface RockergroupRepository : JpaRepository<Rockergroup, Long> {
    @EntityGraph(attributePaths = ["gym"])
    override fun findAll(pageable: Pageable): Page<Rockergroup>

    @EntityGraph(attributePaths = ["gym"])
    override fun findById(id: Long): java.util.Optional<Rockergroup>

    @EntityGraph(attributePaths = ["gym"])
    fun findBygymId(gym: Long): List<Rockergroup>

    @EntityGraph(attributePaths = ["gym"])
    fun findByName(name: String): List<Rockergroup>

    @EntityGraph(attributePaths = ["gym"])
    fun findByDate(date: LocalDateTime): List<Rockergroup>
}
