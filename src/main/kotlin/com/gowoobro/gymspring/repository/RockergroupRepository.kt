package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rockergroup
import com.gowoobro.gymspring.entity.RockergroupCreateRequest
import com.gowoobro.gymspring.entity.RockergroupUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface RockergroupRepository : JpaRepository<Rockergroup, Long> {
    @Query("SELECT m FROM Rockergroup m LEFT JOIN FETCH m.gym")
    override fun findAll(pageable: Pageable): Page<Rockergroup>

    @Query("SELECT m FROM Rockergroup m LEFT JOIN FETCH m.gym WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Rockergroup>

    @Query("SELECT m FROM Rockergroup m LEFT JOIN FETCH m.gym WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Rockergroup>

    @Query("SELECT m FROM Rockergroup m LEFT JOIN FETCH m.gym WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Rockergroup>

    @Query("SELECT m FROM Rockergroup m LEFT JOIN FETCH m.gym WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Rockergroup>
}