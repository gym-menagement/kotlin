package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Daytype
import com.gowoobro.gymspring.entity.DaytypeCreateRequest
import com.gowoobro.gymspring.entity.DaytypeUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface DaytypeRepository : JpaRepository<Daytype, Long> {
    @Query("SELECT m FROM Daytype m LEFT JOIN FETCH m.gym")
    override fun findAll(pageable: Pageable): Page<Daytype>

    @Query("SELECT m FROM Daytype m LEFT JOIN FETCH m.gym WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Daytype>

    @Query("SELECT m FROM Daytype m LEFT JOIN FETCH m.gym WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Daytype>

    @Query("SELECT m FROM Daytype m LEFT JOIN FETCH m.gym WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Daytype>

    @Query("SELECT m FROM Daytype m LEFT JOIN FETCH m.gym WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Daytype>
}