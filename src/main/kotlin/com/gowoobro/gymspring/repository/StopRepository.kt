package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Stop
import com.gowoobro.gymspring.entity.StopCreateRequest
import com.gowoobro.gymspring.entity.StopUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface StopRepository : JpaRepository<Stop, Long> {
    @Query("SELECT m FROM Stop m LEFT JOIN FETCH m.usehealth")
    override fun findAll(pageable: Pageable): Page<Stop>

    @Query("SELECT m FROM Stop m LEFT JOIN FETCH m.usehealth WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Stop>

    @Query("SELECT m FROM Stop m LEFT JOIN FETCH m.usehealth WHERE m.usehelthId = :usehealth")
    fun findByUsehelthWithJoin(usehealth: Long): List<Stop>

    @Query("SELECT m FROM Stop m LEFT JOIN FETCH m.usehealth WHERE m.startday = :startday")
    fun findByStartdayWithJoin(startday: LocalDateTime): List<Stop>

    @Query("SELECT m FROM Stop m LEFT JOIN FETCH m.usehealth WHERE m.endday = :endday")
    fun findByEnddayWithJoin(endday: LocalDateTime): List<Stop>

    @Query("SELECT m FROM Stop m LEFT JOIN FETCH m.usehealth WHERE m.count = :count")
    fun findByCountWithJoin(count: Int): List<Stop>

    @Query("SELECT m FROM Stop m LEFT JOIN FETCH m.usehealth WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Stop>
}