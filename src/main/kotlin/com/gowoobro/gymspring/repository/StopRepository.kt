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
    override fun findAll(pageable: Pageable): Page<Stop>


    fun findByUsehelth(usehelth: Long): List<Stop>

    fun findByStartday(startday: LocalDateTime): List<Stop>

    fun findByEndday(endday: LocalDateTime): List<Stop>

    fun findByCount(count: Int): List<Stop>

    fun findByDate(date: LocalDateTime): List<Stop>
}