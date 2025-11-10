package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface UsehealthRepository : JpaRepository<Usehealth, Long> {
    override fun findAll(pageable: Pageable): Page<Usehealth>


    fun findByOrder(order: Long): List<Usehealth>

    fun findByHealth(health: Long): List<Usehealth>

    fun findByUser(user: Long): List<Usehealth>

    fun findByRocker(rocker: Long): List<Usehealth>

    fun findByTerm(term: Long): List<Usehealth>

    fun findByDiscount(discount: Long): List<Usehealth>

    fun findByStartday(startday: LocalDateTime): List<Usehealth>

    fun findByEndday(endday: LocalDateTime): List<Usehealth>

    fun findByDate(date: LocalDateTime): List<Usehealth>
}