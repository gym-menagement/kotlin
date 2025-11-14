package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rocker
import com.gowoobro.gymspring.entity.RockerCreateRequest
import com.gowoobro.gymspring.entity.RockerUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.rocker.Available


@Repository
interface RockerRepository : JpaRepository<Rocker, Long> {
    @Query("SELECT m FROM Rocker m LEFT JOIN FETCH m.rockergroup")
    override fun findAll(pageable: Pageable): Page<Rocker>

    @Query("SELECT m FROM Rocker m LEFT JOIN FETCH m.rockergroup WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Rocker>

    @Query("SELECT m FROM Rocker m LEFT JOIN FETCH m.rockergroup WHERE m.groupId = :rockergroup")
    fun findByGroupWithJoin(rockergroup: Long): List<Rocker>

    @Query("SELECT m FROM Rocker m LEFT JOIN FETCH m.rockergroup WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Rocker>

    @Query("SELECT m FROM Rocker m LEFT JOIN FETCH m.rockergroup WHERE m.available = :available")
    fun findByAvailableWithJoin(available: Available): List<Rocker>

    @Query("SELECT m FROM Rocker m LEFT JOIN FETCH m.rockergroup WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Rocker>
}